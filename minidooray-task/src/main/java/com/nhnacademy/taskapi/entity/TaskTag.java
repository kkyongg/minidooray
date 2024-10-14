package com.nhnacademy.taskapi.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TaskTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @NotNull
    private Task taskId;

    @ManyToOne
    @NotNull
    private Tag tagId;

    public TaskTag(Task taskId, Tag tagId) {
        this.taskId = taskId;
        this.tagId = tagId;
    }
}
