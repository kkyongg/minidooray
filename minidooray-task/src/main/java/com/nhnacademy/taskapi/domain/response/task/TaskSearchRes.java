package com.nhnacademy.taskapi.domain.response.task;

import com.nhnacademy.taskapi.domain.dto.comment.CommentSearchDto;
import com.nhnacademy.taskapi.domain.dto.milestone.MileStoneIdStatusDto;
import com.nhnacademy.taskapi.domain.dto.tag.TagIdNameDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TaskSearchRes {

    private String name;
    private String content;
    private long userId;
    private long projectId;
    private List<TagIdNameDto> tag;
    private MileStoneIdStatusDto milestone;
    private List<CommentSearchDto> comment;

}
