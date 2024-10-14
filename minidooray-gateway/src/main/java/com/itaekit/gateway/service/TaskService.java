package com.itaekit.gateway.service;

import com.itaekit.gateway.dto.project.request.CreateProjectRequestDto;
import com.itaekit.gateway.dto.project.response.CreateProjectResponseDto;
import com.itaekit.gateway.dto.project.response.SearchProjectResponseDto;

import java.util.List;

public interface TaskService {
    CreateProjectResponseDto registerProject(CreateProjectRequestDto requestDto);
    List<SearchProjectResponseDto> findAllProject(Long userId);
}
