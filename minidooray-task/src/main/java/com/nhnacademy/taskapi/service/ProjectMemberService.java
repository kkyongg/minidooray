package com.nhnacademy.taskapi.service;


import com.nhnacademy.taskapi.entity.ProjectMember;
import com.nhnacademy.taskapi.repository.ProjectMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectMemberService {

    private final ProjectMemberRepository projectMemberRepository;


    public ProjectMember saveProjectMember(ProjectMember projectMember) {
        ProjectMember savedProjectMember = projectMemberRepository.save(projectMember);

        return savedProjectMember;
    }

    public ProjectMember findById(long id) {
        return projectMemberRepository.findById(id).get();
    }

}
