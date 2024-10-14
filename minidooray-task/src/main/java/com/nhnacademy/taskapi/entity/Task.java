package com.nhnacademy.taskapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Setter
    private String name;

    @NotNull
    @Setter
    private String content;


    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private long userId;

    @ManyToOne
    private Project projectId;

    public Task(String name, String content, LocalDateTime createdAt, long userId, Project projectId) {
        this.name = name;
        this.content = content;
        this.createdAt = createdAt;
        this.userId = userId;
        this.projectId = projectId;
    }

    @OneToMany(mappedBy = "taskId",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<TaskTag> taskTags = new ArrayList<>();

    public void addTaskTag(TaskTag taskTag) {
        taskTags.add(taskTag);
    }

    @OneToOne(mappedBy = "taskId", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Setter
    private TaskMileStone taskMileStone;

    @OneToMany(mappedBy = "taskId", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
