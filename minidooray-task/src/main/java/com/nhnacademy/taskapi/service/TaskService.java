package com.nhnacademy.taskapi.service;

import com.nhnacademy.taskapi.domain.dto.task.TaskDto;
import com.nhnacademy.taskapi.domain.dto.task.TaskIdDto;
import com.nhnacademy.taskapi.entity.Task;
import com.nhnacademy.taskapi.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<TaskDto> findAllTaskInProject(long projectId){
        List<TaskDto> tasks = taskRepository.findAllByProjectId_Id(projectId);

        return tasks;

    }

    public TaskIdDto saveTask(Task task) {
        Task savedTask = taskRepository.save(task);
        Optional<TaskIdDto> taskIdDtoById = taskRepository.findTaskIdDtoById(savedTask.getId());

        return taskIdDtoById.get();

    }

    public Task findById(long id) {
        Optional<Task> savedTask = taskRepository.findById(id);

        return savedTask.get();
    }

    public void deleteTaskById(long taskId) {
        taskRepository.deleteById(taskId);

    }
}
