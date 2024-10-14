package com.nhnacademy.taskapi.domain.dto.project;

import com.nhnacademy.taskapi.entity.ProjectStatus;

public interface ProjectEditDto {

    String getName();

    ProjectStatus getStatus();
}
