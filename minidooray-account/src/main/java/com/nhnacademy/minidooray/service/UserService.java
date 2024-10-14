package com.nhnacademy.minidooray.service;

import com.nhnacademy.minidooray.dto.UserEditRequest;
import com.nhnacademy.minidooray.dto.UserRegisterRequest;
import com.nhnacademy.minidooray.entity.User;

import java.util.List;

//회원 관련 정보 서비스 처리
public interface UserService {
    //회원가입
    void registerUser(UserRegisterRequest request);

    //로그인
    User doLogin(String userId);

    //조회
    User getUser(String userId);

    //휴면 회원 조회
    void updateOldUsersToInactive();

    //수정
    void editUser(String userId, UserEditRequest request);

    //삭제
    void deleteUser(String userId);

}
