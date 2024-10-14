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
public class MileStone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String status;

    @ManyToOne
    @NotNull
    private Project projectId;

    public MileStone(String status, Project projectId) {
        this.status = status;
        this.projectId = projectId;
    }

    @OneToMany(mappedBy = "mileStoneId",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<TaskMileStone> taskMileStones = new ArrayList<>();

    public void addTaskMileStone(TaskMileStone taskMileStone) {
        taskMileStones.add(taskMileStone);
    }
}
