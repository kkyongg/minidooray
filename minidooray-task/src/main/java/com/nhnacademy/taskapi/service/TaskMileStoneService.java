package com.nhnacademy.taskapi.service;

import com.nhnacademy.taskapi.entity.TaskMileStone;
import com.nhnacademy.taskapi.repository.TaskMileStoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskMileStoneService {

    private final TaskMileStoneRepository taskMileStoneRepository;

    public TaskMileStone saveTaskMileStone(TaskMileStone taskMileStone) {
        TaskMileStone savedTaskMileStone = taskMileStoneRepository.save(taskMileStone);

        return savedTaskMileStone;
    }

    public void deleteAllByTaskId(long taskId) {
        taskMileStoneRepository.deleteAllByTaskId_Id(taskId);
    }

    public TaskMileStone findByTaskId(long taskId) {
        Optional<TaskMileStone> taskMileStone = taskMileStoneRepository.findByTaskId_Id(taskId);

        return taskMileStone.get();
    }
}
