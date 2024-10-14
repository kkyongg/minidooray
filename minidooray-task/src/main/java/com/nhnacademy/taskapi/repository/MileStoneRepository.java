package com.nhnacademy.taskapi.repository;

import com.nhnacademy.taskapi.domain.dto.milestone.MileStoneIdStatusDto;
import com.nhnacademy.taskapi.entity.MileStone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MileStoneRepository extends JpaRepository<MileStone,Long> {

    @Query("select m.id as id, m.status as status from MileStone m join TaskMileStone tm on m.id = tm.mileStoneId.id where tm.taskId.id = :taskId")
    Optional<MileStoneIdStatusDto> findMileStoneIdStatusByTaskId(long taskId);


    List<MileStoneIdStatusDto> findAllMileStoneIdStatusByProjectId_Id(long projectId);
}
