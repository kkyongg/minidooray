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
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Setter
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Setter
    private ProjectStatus status;

    @NotNull
    private LocalDateTime createdAt;


    @NotNull
    private long adminId;

    public Project(String name, ProjectStatus status, LocalDateTime createdAt, long adminId) {
        this.name = name;
        this.status = status;
        this.createdAt = createdAt;
        this.adminId = adminId;
    }

    @OneToMany(mappedBy = "projectId", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }


    @OneToMany(mappedBy = "projectId", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Tag> tags = new ArrayList<>();
    public void addTag(Tag tag) {
        tags.add(tag);
    }


    @OneToMany(mappedBy = "projectId",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ProjectMember> projectMembers = new ArrayList<>();

    public void addProjectMember(ProjectMember projectMember) {
        projectMembers.add(projectMember);
    }


    @OneToMany(mappedBy = "projectId",fetch = FetchType.EAGER ,cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<MileStone> mileStones = new ArrayList<>();

    public void addMileStone(MileStone mileStone) {
        mileStones.add(mileStone);
    }
}
