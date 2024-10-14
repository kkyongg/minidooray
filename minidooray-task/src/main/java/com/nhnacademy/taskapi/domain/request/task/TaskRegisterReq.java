package com.nhnacademy.taskapi.domain.request.task;

import com.nhnacademy.taskapi.domain.request.taskMileStone.TaskMileStoneRegisterReq;
import com.nhnacademy.taskapi.domain.request.taskTag.TaskTagRegisterReq;
import lombok.Data;

import java.util.List;

@Data
public class TaskRegisterReq {

    private String name;
    private String content;
    private long userId;
    private long projectId;
    private List<TaskTagRegisterReq> tag;
    private TaskMileStoneRegisterReq milestone;
}
