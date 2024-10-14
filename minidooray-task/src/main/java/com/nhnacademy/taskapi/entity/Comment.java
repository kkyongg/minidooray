package com.nhnacademy.taskapi.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String content;

    @NotNull
    private LocalDateTime createdAt;

    @ManyToOne
    @NotNull
    private Task taskId;

    @ManyToOne
    @NotNull
    private ProjectMember projectMemberId;

    public Comment(String content, LocalDateTime createdAt, Task taskId, ProjectMember projectMemberId) {
        this.content = content;
        this.createdAt = createdAt;
        this.taskId = taskId;
        this.projectMemberId = projectMemberId;
    }
}
