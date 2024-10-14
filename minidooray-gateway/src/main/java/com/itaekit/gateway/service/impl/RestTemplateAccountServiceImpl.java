package com.itaekit.gateway.service.impl;

import com.itaekit.gateway.dto.account.request.CreateAccountRequestDto;
import com.itaekit.gateway.dto.account.request.EditAccountRequestDto;
import com.itaekit.gateway.dto.account.request.LoginAccountRequestDto;
import com.itaekit.gateway.dto.account.response.CreateAccountResponseDto;
import com.itaekit.gateway.dto.account.response.EditAccountResponseDto;
import com.itaekit.gateway.dto.account.response.LoginAccountResponseDto;
import com.itaekit.gateway.dto.user.UserDto;
import com.itaekit.gateway.exception.RegisterFailException;
import com.itaekit.gateway.exception.UserRequestFailException;
import com.itaekit.gateway.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
public class RestTemplateAccountServiceImpl implements AccountService {
    private final BCryptPasswordEncoder passwordEncoder;

    public RestTemplateAccountServiceImpl(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CreateAccountResponseDto registerUser(CreateAccountRequestDto requestDto) {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8282")
                .path("/account/api/account/register")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        ResponseEntity<CreateAccountResponseDto> responseEntity = restTemplate.postForEntity(uri, requestDto, CreateAccountResponseDto.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            throw new RegisterFailException("회원가입에 실패하였습니다.");
        }
    }

    @Override
    public EditAccountResponseDto editUser(EditAccountRequestDto requestDto) {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8282")
                .path("/account/api/edit/" + requestDto.getUserId())
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        RequestEntity<EditAccountRequestDto> requestEntity = RequestEntity.put(uri).body(requestDto);
        ResponseEntity<EditAccountResponseDto> responseEntity = restTemplate.exchange(requestEntity, EditAccountResponseDto.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            throw new UserRequestFailException("회원 정보 변경에 실패하였습니다.");
        }
    }

    @Override
    public LoginAccountResponseDto findUserByUserId(LoginAccountRequestDto request) {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8282")
                .path("/account/api/login")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<LoginAccountResponseDto> responseEntity = restTemplate.postForEntity(uri, request, LoginAccountResponseDto.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            throw new UserRequestFailException("로그인에 실패하였습니다.");
        }
    }

    @Override
    public UserDto getUserDetailsByUserId(String userId) {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8282")
                .path("/account/api/mypage/" + userId)
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserDto> responseEntity = restTemplate.getForEntity(uri, UserDto.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            throw new UserRequestFailException("회원 정보 조회에 실패하였습니다.");
        }
    }

    @Override
    public void removeUser(String userId) {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8282")
                .path("/account/api/resign/" + userId)
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<Void> requestEntity = RequestEntity.delete(uri).build();
        ResponseEntity<HttpStatus> responseEntity = restTemplate.exchange(requestEntity, HttpStatus.class);

        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new UserRequestFailException("회원 탈퇴에 실패하였습니다.");
        }
    }
}
