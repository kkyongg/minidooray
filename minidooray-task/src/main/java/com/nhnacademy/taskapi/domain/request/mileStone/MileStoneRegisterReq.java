package com.nhnacademy.taskapi.domain.request.mileStone;

import lombok.Data;

@Data
public class MileStoneRegisterReq {
    private long projectId;
    private String status;

}
