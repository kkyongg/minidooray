package com.nhnacademy.taskapi.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;

    @ManyToOne
    private Project projectId;

    public Tag(String name, Project projectId) {
        this.name = name;
        this.projectId = projectId;
    }

    @OneToMany(mappedBy = "tagId",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<TaskTag> taskTags = new ArrayList<>();

    public void addTaskTag(TaskTag taskTag) {
        taskTags.add(taskTag);
    }
}
