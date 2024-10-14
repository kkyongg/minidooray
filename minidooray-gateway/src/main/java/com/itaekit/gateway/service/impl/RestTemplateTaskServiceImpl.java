package com.itaekit.gateway.service.impl;

import com.itaekit.gateway.dto.account.request.CreateAccountRequestDto;
import com.itaekit.gateway.dto.account.request.EditAccountRequestDto;
import com.itaekit.gateway.dto.account.response.CreateAccountResponseDto;
import com.itaekit.gateway.dto.account.response.EditAccountResponseDto;
import com.itaekit.gateway.dto.project.request.CreateProjectRequestDto;
import com.itaekit.gateway.dto.project.response.CreateProjectResponseDto;
import com.itaekit.gateway.dto.project.response.SearchProjectResponseDto;
import com.itaekit.gateway.exception.FindFailException;
import com.itaekit.gateway.exception.RegisterFailException;
import com.itaekit.gateway.service.TaskService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class RestTemplateTaskServiceImpl implements TaskService {
    @Override
    public CreateProjectResponseDto registerProject(CreateProjectRequestDto requestDto) {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8282")
                .path("/task/api/project/register")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<CreateProjectRequestDto> requestEntity = RequestEntity.post(uri).body(requestDto);
        ResponseEntity<CreateProjectResponseDto> responseEntity = restTemplate.exchange(requestEntity, CreateProjectResponseDto.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            throw new RegisterFailException("프로젝트 등록에 실패하였습니다.");
        }
    }

    @Override
    public List<SearchProjectResponseDto> findAllProject(Long userId) {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8282")
                .path("/task/api/projects/" + userId)
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<Void> requestEntity = RequestEntity.get(uri).build();
        ResponseEntity<List<SearchProjectResponseDto>> responseEntity = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<List<SearchProjectResponseDto>>() {});

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            throw new FindFailException("프로젝트 조회에 실패하였습니다.");
        }

    }
}
