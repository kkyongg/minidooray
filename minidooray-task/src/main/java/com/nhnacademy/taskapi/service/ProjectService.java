package com.nhnacademy.taskapi.service;

import com.nhnacademy.taskapi.domain.dto.project.ProjectDto;
import com.nhnacademy.taskapi.domain.dto.project.ProjectEditDto;
import com.nhnacademy.taskapi.domain.dto.project.ProjectIdDto;
import com.nhnacademy.taskapi.entity.Project;
import com.nhnacademy.taskapi.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    // project save
    public ProjectIdDto saveProject(Project project){
        Project savedProject = projectRepository.save(project);

        Optional<ProjectIdDto> projectIdDto = projectRepository.findProjectIdDtoById(savedProject.getId());

        if (Objects.isNull(projectIdDto.get())) {
            return null;
        }

        return projectIdDto.get();
    }

    // projects by userId
    public List<ProjectDto> findAllProjectsByUser(long userId){
        List<ProjectDto> projectDtoList = projectRepository.findAllProjectsByUserId(userId);

        return projectDtoList;
    }

    public ProjectEditDto findProjectEditDto(long projectId) {
        Optional<ProjectEditDto> projectEditDto = projectRepository.findProjectEditDtoById(projectId);
        if (Objects.isNull(projectEditDto.get())) {
            return null;
        }

        return projectEditDto.get();
    }

    // project by id
    public Project findById(long projectId){
        Optional<Project> project = projectRepository.findById(projectId);

        if (Objects.isNull(project.get())) {
            return null;
        }

        return project.get();

    }

    // delete project
    public void deleteProject(long projectId) {
        projectRepository.deleteById(projectId);

    }


}
