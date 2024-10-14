package com.nhnacademy.taskapi.repository;

import com.nhnacademy.taskapi.entity.TaskMileStone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskMileStoneRepository extends JpaRepository<TaskMileStone,Long> {

    void deleteAllByTaskId_Id(long taskId);

    Optional<TaskMileStone> findByTaskId_Id(long taskid);
}
