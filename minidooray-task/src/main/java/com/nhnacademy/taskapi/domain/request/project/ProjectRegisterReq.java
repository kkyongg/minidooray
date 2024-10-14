package com.nhnacademy.taskapi.domain.request.project;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class ProjectRegisterReq {


    private String name;
    private long adminId;
}
