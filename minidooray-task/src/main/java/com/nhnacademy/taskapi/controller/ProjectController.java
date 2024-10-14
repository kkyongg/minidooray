package com.nhnacademy.taskapi.controller;


import com.nhnacademy.taskapi.domain.dto.project.ProjectDto;
import com.nhnacademy.taskapi.domain.dto.project.ProjectEditDto;
import com.nhnacademy.taskapi.domain.dto.project.ProjectIdDto;
import com.nhnacademy.taskapi.domain.dto.projectMember.ProjectMemberDto;
import com.nhnacademy.taskapi.domain.dto.task.TaskDto;
import com.nhnacademy.taskapi.domain.request.project.ProjectEditReq;
import com.nhnacademy.taskapi.domain.request.project.ProjectRegisterReq;
import com.nhnacademy.taskapi.entity.MileStone;
import com.nhnacademy.taskapi.entity.Project;
import com.nhnacademy.taskapi.entity.ProjectStatus;
import com.nhnacademy.taskapi.service.MileStoneService;
import com.nhnacademy.taskapi.service.ProjectService;
import com.nhnacademy.taskapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("task")

public class ProjectController {

    private final ProjectService projectService;
    //    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final MileStoneService mileStoneService;

    // project register
    @PostMapping("/api/project/register")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpEntity<ProjectIdDto> projectRegister(@RequestBody ProjectRegisterReq registerReq) {
        Project project = new Project(registerReq.getName(), ProjectStatus.ACTIVE, LocalDateTime.now(), registerReq.getAdminId());

        ProjectIdDto projectIdDto = projectService.saveProject(project);
        Project savedProject = projectService.findById(projectIdDto.getId());
        mileStoneService.saveMileStone(new MileStone("start", savedProject));
        mileStoneService.saveMileStone(new MileStone("end", savedProject));
        if (Objects.isNull(projectIdDto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }



        return new HttpEntity<>(projectIdDto);
    }

    // all projects by userId
    @GetMapping("/api/projects/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public HttpEntity<List<ProjectDto>> findAllProjectsByUser(@PathVariable("userId") long userId) {
        List<ProjectDto> projectsByUser = projectService.findAllProjectsByUser(userId);

        return new HttpEntity<>(projectsByUser);
    }

    // project edit form
    @GetMapping("/api/project/edit/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public HttpEntity<ProjectEditDto> editProjectForm(@PathVariable("projectId") long projectId) {
        ProjectEditDto projectEditDto = projectService.findProjectEditDto(projectId);

        return new HttpEntity<>(projectEditDto);
    }


    // project edit
    @PostMapping("/api/project/edit/{projectId}")
    public ResponseEntity<Void> editProject(@PathVariable("projectId") long projectId, @RequestBody ProjectEditReq projectEditReq) {
        Project project = projectService.findById(projectId);
        project.setName(projectEditReq.getName());
        project.setStatus(ProjectStatus.valueOf(projectEditReq.getStatus()));

        projectService.saveProject(project);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // project delete
    @DeleteMapping("/api/project/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable("projectId") long projectId) {
        projectService.deleteProject(projectId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }




}
