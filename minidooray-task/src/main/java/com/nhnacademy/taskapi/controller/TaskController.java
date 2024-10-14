package com.nhnacademy.taskapi.controller;

import com.nhnacademy.taskapi.domain.dto.comment.CommentSearchDto;
import com.nhnacademy.taskapi.domain.dto.milestone.MileStoneIdStatusDto;
import com.nhnacademy.taskapi.domain.dto.tag.TagIdNameDto;
import com.nhnacademy.taskapi.domain.dto.task.TaskDto;
import com.nhnacademy.taskapi.domain.dto.task.TaskIdDto;

import com.nhnacademy.taskapi.domain.request.task.TaskEditReq;
import com.nhnacademy.taskapi.domain.request.task.TaskRegisterReq;
import com.nhnacademy.taskapi.domain.request.taskTag.TaskTagRegisterReq;
import com.nhnacademy.taskapi.domain.response.task.TaskEditFormRes;
import com.nhnacademy.taskapi.domain.response.task.TaskSearchRes;
import com.nhnacademy.taskapi.entity.*;
import com.nhnacademy.taskapi.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("task")
public class TaskController {

    private final TaskService taskService;
    private final ProjectService projectService;
    private final TaskTagService taskTagService;
    private final TaskMileStoneService taskMileStoneService;
    private final TagService tagService;
    private final MileStoneService mileStoneService;
    private final CommentService commentService;


    @PostMapping("/api/task/register")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpEntity<TaskIdDto> registerTask(@RequestBody TaskRegisterReq taskRegisterReq) {
        // task save
        Project project = projectService.findById(taskRegisterReq.getProjectId());
        Task task = new Task(taskRegisterReq.getName(), taskRegisterReq.getContent(), LocalDateTime.now(), taskRegisterReq.getUserId()
                , project);
        TaskIdDto taskIdDto = taskService.saveTask(task);
        Task savedTask = taskService.findById(taskIdDto.getId());
        project.addTask(savedTask);

        // taskTag save
        for (TaskTagRegisterReq taskTagRegisterReq : taskRegisterReq.getTag()) {
            Tag tag = tagService.findById(taskTagRegisterReq.getId());
            TaskTag savedTaskTag = taskTagService.saveTaskTag(new TaskTag(taskService.findById(taskIdDto.getId()), tag));
            tag.addTaskTag(savedTaskTag);
            savedTask.addTaskTag(savedTaskTag);

        }

        // taskMileStone save
        MileStone mileStone;
        if (taskRegisterReq.getMilestone().getId() == 0) {
            mileStone = mileStoneService.findById(1);
        }else{
            mileStone = mileStoneService.findById(taskRegisterReq.getMilestone().getId());
        }

        TaskMileStone savedTaskMileStone = taskMileStoneService.saveTaskMileStone(new TaskMileStone(taskService.findById(taskIdDto.getId())
                , mileStone));
        mileStone.addTaskMileStone(savedTaskMileStone);
        savedTask.setTaskMileStone(savedTaskMileStone);
        return new HttpEntity<>(taskIdDto);
    }

    //  tasks in project
    @GetMapping("/api/project/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public HttpEntity<List<TaskDto>> findAllTasksInProject(@PathVariable("projectId") long id) {

        List<TaskDto> tasks = taskService.findAllTaskInProject(id);

        return new HttpEntity<>(tasks);
    }


    // search task
    @GetMapping("/api/task/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public HttpEntity<TaskSearchRes> findTask(@PathVariable("taskId") long taskId) {
        Task task = taskService.findById(taskId);

        List<TagIdNameDto> tagIdNameDtoList = tagService.findALlTagIdNameByTaskId(taskId);

        MileStoneIdStatusDto mileStoneIdStatusDto = mileStoneService.findMileStoneByTaskId(taskId);

        List<CommentSearchDto> commentSearchDtoList = commentService.findAllCommentDtoByTaskId(taskId);

        TaskSearchRes taskSearchRes = new TaskSearchRes(task.getName(), task.getContent(), task.getUserId(), task.getProjectId().getId(), tagIdNameDtoList, mileStoneIdStatusDto, commentSearchDtoList);


        return new HttpEntity<>(taskSearchRes);

    }

    // task edit form
    @GetMapping("/api/task/edit/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public HttpEntity<TaskEditFormRes> editTaskForm(@PathVariable("taskId") long taskId) {
        Task task = taskService.findById(taskId);


        List<TagIdNameDto> tagIdNameDtoList = tagService.findALlTagIdNameByTaskId(taskId);

        MileStoneIdStatusDto mileStoneIdStatusDto = mileStoneService.findMileStoneByTaskId(taskId);

        List<TagIdNameDto> tagIdNameByProjectId = tagService.findALlTagIdNameByProjectId(task.getProjectId().getId());
        List<MileStoneIdStatusDto> mileStoneIdStatusByProjectId = mileStoneService.findAllMileStoneIdStatusByProjectId(task.getProjectId().getId());


        TaskEditFormRes taskEditFormRes = new TaskEditFormRes(task.getName(), task.getContent(), tagIdNameDtoList, mileStoneIdStatusDto, tagIdNameByProjectId, mileStoneIdStatusByProjectId);

        return new HttpEntity<>(taskEditFormRes);
    }

    // task edit
    @PostMapping("/api/task/edit/{taskId}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> editTask(@PathVariable("taskId") long taskId, @RequestBody TaskEditReq taskEditReq) {

        //task 수정
        Task task = taskService.findById(taskId);
        task.setName(taskEditReq.getName());
        task.setContent(taskEditReq.getContent());
        taskService.saveTask(task);
        Task savedTask = taskService.findById(task.getId());

        taskTagService.deleteAllByTaskId(taskId);


        // taskTag 수정
        List<TaskTagRegisterReq> tagList = taskEditReq.getTag();

        for (TaskTagRegisterReq tag : tagList) {
            Tag savedTag = tagService.findById(tag.getId());
            taskTagService.saveTaskTag(new TaskTag(savedTask, savedTag));
        }


        // taskMilestone 수정
        TaskMileStone taskMileStone = taskMileStoneService.findByTaskId(savedTask.getId());
        taskMileStone.setMileStoneId(mileStoneService.findById(taskEditReq.getMilestone().getId()));
        taskMileStoneService.saveTaskMileStone(taskMileStone);


        return ResponseEntity.status(HttpStatus.OK).build();
    }


    // task 삭제
    @DeleteMapping("/api/task/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable("taskId") long taskId) {
        taskService.deleteTaskById(taskId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
