package com.nhnacademy.taskapi.controller;

import com.nhnacademy.taskapi.domain.request.tag.TagRegisterReq;
import com.nhnacademy.taskapi.entity.Project;
import com.nhnacademy.taskapi.entity.Tag;
import com.nhnacademy.taskapi.service.ProjectService;
import com.nhnacademy.taskapi.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("task")
public class TagController {
    private final TagService tagService;
    private final ProjectService projectService;

    @PostMapping("/api/tag/register")
    public ResponseEntity<Void> registerTag(@RequestBody TagRegisterReq tagRegisterReq) {
        Project project = projectService.findById(tagRegisterReq.getProjectId());
        Tag tag = new Tag(tagRegisterReq.getName(), project);
        Tag savedTag = tagService.saveTag(tag);

        project.addTag(savedTag);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @DeleteMapping("/api/tag/{tagId}")
    public ResponseEntity<Void> deleteTag(@PathVariable("tagId") long tagId) {
        tagService.deleteTagById(tagId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
