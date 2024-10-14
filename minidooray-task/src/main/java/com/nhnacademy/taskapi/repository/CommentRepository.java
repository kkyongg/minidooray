package com.nhnacademy.taskapi.repository;

import com.nhnacademy.taskapi.domain.dto.comment.CommentSearchDto;
import com.nhnacademy.taskapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<CommentSearchDto> findAllCommentDtoByTaskId_Id(long taskId);
}
