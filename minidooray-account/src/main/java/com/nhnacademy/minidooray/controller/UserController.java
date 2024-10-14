package com.nhnacademy.minidooray.controller;

import com.nhnacademy.minidooray.dto.*;
import com.nhnacademy.minidooray.entity.User;
import com.nhnacademy.minidooray.exception.UserNotFoundException;
import com.nhnacademy.minidooray.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class UserController {

    private final UserService userService;

    //회원가입 처리
    @PostMapping("/api/account/register")
    public ResponseEntity<UserRegisterResponse> registerUser(@Valid @RequestBody UserRegisterRequest request) {
        userService.registerUser(request);
        return ResponseEntity.status(HttpStatus.OK).body(new UserRegisterResponse(request.getUserId()));
    }

    //로그인 처리
    @PostMapping("/api/login")
    public ResponseEntity<UserLoginResponse> login(@Valid @RequestBody UserLoginRequest request) {
        User user = userService.doLogin(request.getUserId());
        return ResponseEntity.status(HttpStatus.OK).body(new UserLoginResponse(user));
    }

    //회원정보 조회
    @GetMapping("/api/mypage/{userId}")
    public ResponseEntity<UserMypageResponse> mypage(@PathVariable String userId) {
        User user = userService.getUser(userId);

        if (Objects.isNull(user)) {
            throw new UserNotFoundException("user not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(new UserMypageResponse(user));
    }

    //회원 정보 수정
    @PutMapping("/api/edit/{userId}")
    public ResponseEntity<UserEditResponse> editUser(@PathVariable String userId, @Valid @RequestBody UserEditRequest request) {
        userService.editUser(userId, request);
        return ResponseEntity.status(HttpStatus.OK).body(new UserEditResponse(request.getId(), request.getUserId(), request.getEmail(), request.getPassword(), request.getStatus()));
    }

    //회원 탈퇴(상태 변경)
    @DeleteMapping("/api/resign/{userId}")
    public ResponseEntity<HttpStatus> resignUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
