package com.nhnacademy.taskapi.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TaskMileStone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @NotNull
    private Task taskId;

    @ManyToOne
    @NotNull
    @Setter
    private MileStone mileStoneId;

    public TaskMileStone(Task taskId, MileStone mileStoneId) {
        this.taskId = taskId;
        this.mileStoneId = mileStoneId;
    }
}
