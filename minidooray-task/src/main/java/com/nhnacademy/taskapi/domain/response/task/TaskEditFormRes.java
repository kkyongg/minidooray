package com.nhnacademy.taskapi.domain.response.task;

import com.nhnacademy.taskapi.domain.dto.milestone.MileStoneIdStatusDto;
import com.nhnacademy.taskapi.domain.dto.tag.TagIdNameDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TaskEditFormRes {

    private String name;
    private String content;
    private List<TagIdNameDto> tag;
    private MileStoneIdStatusDto milestone;
    private List<TagIdNameDto> tags;
    private List<MileStoneIdStatusDto> milestones;

}
