package com.nhnacademy.taskapi.domain.request.comment;

import lombok.Data;

@Data
public class CommentRegisterReq {
    private String content;
    private long taskId;
    private long projectMemberId;

}
