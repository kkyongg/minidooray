package com.nhnacademy.taskapi.repository;

import com.nhnacademy.taskapi.domain.dto.project.ProjectDto;
import com.nhnacademy.taskapi.domain.dto.project.ProjectEditDto;
import com.nhnacademy.taskapi.domain.dto.project.ProjectIdDto;
import com.nhnacademy.taskapi.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project,Long> {

    Optional<ProjectIdDto> findProjectIdDtoById(long id);

    Optional<ProjectEditDto> findProjectEditDtoById(long id);

    @Query("select p.id as id ,p.name as name from Project p join ProjectMember pm on p.id = pm.projectId.id where pm.memberId = :userId")
    List<ProjectDto> findAllProjectsByUserId(long userId);
}
