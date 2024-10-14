package com.nhnacademy.taskapi.controller;

import com.nhnacademy.taskapi.domain.request.mileStone.MileStoneRegisterReq;
import com.nhnacademy.taskapi.entity.MileStone;
import com.nhnacademy.taskapi.entity.Project;
import com.nhnacademy.taskapi.service.MileStoneService;
import com.nhnacademy.taskapi.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("task")

public class MileStoneController {
    private final MileStoneService mileStoneService;
    private final ProjectService projectService;

    @PostMapping("/api/milestone/register")
    public ResponseEntity<Void> registerMileStone(@RequestBody MileStoneRegisterReq mileStoneRegisterReq) {
        Project project = projectService.findById(mileStoneRegisterReq.getProjectId());
        MileStone mileStone = new MileStone(mileStoneRegisterReq.getStatus(), project);
        MileStone savedMileStone = mileStoneService.saveMileStone(mileStone);
        project.addMileStone(savedMileStone);

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @DeleteMapping("/api/milestone/{milestoneId}")
    public ResponseEntity<Void> deleteMileStoneById(@PathVariable("milestoneId") long milestoneId) {
        mileStoneService.deleteById(milestoneId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
