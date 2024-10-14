package com.nhnacademy.taskapi.repository;

import com.nhnacademy.taskapi.entity.TaskTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  TaskTagRepository extends JpaRepository<TaskTag,Long> {


    void deleteAllByTaskId_Id(long taskId);
}
