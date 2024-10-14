package com.nhnacademy.taskapi.controller;


import com.nhnacademy.taskapi.domain.dto.projectMember.ProjectMemberDto;
import com.nhnacademy.taskapi.entity.Project;
import com.nhnacademy.taskapi.entity.ProjectMember;
import com.nhnacademy.taskapi.service.ProjectMemberService;
import com.nhnacademy.taskapi.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("task")
public class ProjectMemberController {


    private final ProjectMemberService projectMemberService;
    private final ProjectService projectService;


    @PostMapping("/api/project/{projectId}/addMember/{memberId}")
    public ResponseEntity<Void> registerMemberToProject(@PathVariable("projectId") long projectId, @PathVariable("memberId") long memberId) {
        Project project = projectService.findById(projectId);
        ProjectMember projectMember = new ProjectMember(project, memberId);
        ProjectMember savedProjectMember = projectMemberService.saveProjectMember(projectMember);

        project.addProjectMember(savedProjectMember);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
}
