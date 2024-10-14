package com.nhnacademy.taskapi.domain.request.tag;

import lombok.Data;

@Data
public class TagRegisterReq {
    private String name;
    private long projectId;

}
