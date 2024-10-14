package com.nhnacademy.taskapi.repository;

import com.nhnacademy.taskapi.domain.dto.project.ProjectIdDto;
import com.nhnacademy.taskapi.domain.dto.task.TaskDto;
import com.nhnacademy.taskapi.domain.dto.task.TaskIdDto;
import com.nhnacademy.taskapi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<TaskDto> findAllByProjectId_Id(long id);


    Optional<TaskIdDto> findTaskIdDtoById(long id);
}
