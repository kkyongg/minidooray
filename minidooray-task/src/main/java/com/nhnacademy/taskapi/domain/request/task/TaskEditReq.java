package com.nhnacademy.taskapi.domain.request.task;


import com.nhnacademy.taskapi.domain.dto.milestone.MileStoneIdStatusDto;
import com.nhnacademy.taskapi.domain.dto.tag.TagIdNameDto;
import com.nhnacademy.taskapi.domain.request.mileStone.MileStoneRegisterReq;
import com.nhnacademy.taskapi.domain.request.tag.TagRegisterReq;
import com.nhnacademy.taskapi.domain.request.taskMileStone.TaskMileStoneRegisterReq;
import com.nhnacademy.taskapi.domain.request.taskTag.TaskTagRegisterReq;
import lombok.Data;

import java.util.List;

@Data
public class TaskEditReq {
    private String name;
    private String content;
    private List<TaskTagRegisterReq> tag;
    private TaskMileStoneRegisterReq milestone;

}
