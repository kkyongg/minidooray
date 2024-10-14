package com.nhnacademy.taskapi.service;

import com.nhnacademy.taskapi.domain.dto.comment.CommentSearchDto;
import com.nhnacademy.taskapi.entity.Comment;
import com.nhnacademy.taskapi.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public List<CommentSearchDto> findAllCommentDtoByTaskId(long taskId) {
        List<CommentSearchDto> commentDtoList = commentRepository.findAllCommentDtoByTaskId_Id(taskId);
        return commentDtoList;
    }

    public Comment saveComment(Comment comment) {
        Comment savedComment = commentRepository.save(comment);
        return savedComment;
    }

    public void deleteById(long commentId) {
        commentRepository.deleteById(commentId);

    }
}
