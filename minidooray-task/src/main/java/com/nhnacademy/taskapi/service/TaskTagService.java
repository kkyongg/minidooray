package com.nhnacademy.taskapi.service;

import com.nhnacademy.taskapi.entity.TaskTag;
import com.nhnacademy.taskapi.repository.TaskTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskTagService {

    private final TaskTagRepository taskTagRepository;

    public TaskTag saveTaskTag(TaskTag taskTag) {
        TaskTag savedTaskTag = taskTagRepository.save(taskTag);
        return savedTaskTag;
    }

    public void deleteAllByTaskId(long taskId) {
        taskTagRepository.deleteAllByTaskId_Id(taskId);

    }






}
