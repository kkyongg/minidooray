package com.nhnacademy.taskapi.controller;

import com.nhnacademy.taskapi.domain.request.comment.CommentRegisterReq;
import com.nhnacademy.taskapi.entity.Comment;
import com.nhnacademy.taskapi.entity.ProjectMember;
import com.nhnacademy.taskapi.entity.Task;
import com.nhnacademy.taskapi.service.CommentService;
import com.nhnacademy.taskapi.service.ProjectMemberService;
import com.nhnacademy.taskapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("task")
public class CommentController {
    private final CommentService commentService;
    private final TaskService taskService;
    private final ProjectMemberService projectMemberService;

    @PostMapping("/api/comment/register")
    public ResponseEntity<Void> registerComment(@RequestBody CommentRegisterReq commentRegisterReq) {
        Task task = taskService.findById(commentRegisterReq.getTaskId());
        ProjectMember projectMember = projectMemberService.findById(commentRegisterReq.getProjectMemberId());
        Comment comment = new Comment(commentRegisterReq.getContent(), LocalDateTime.now()
                , task, projectMember);


        Comment savedComment = commentService.saveComment(comment);
        task.addComment(savedComment);
        projectMember.addComment(savedComment);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/api/comment/{commentId}")
    public ResponseEntity<Void> deleteCommentById(@PathVariable("commentId") long commentId) {
        commentService.deleteById(commentId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
