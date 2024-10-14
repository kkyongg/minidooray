package com.itaekit.gateway.service;

import com.itaekit.gateway.dto.account.request.CreateAccountRequestDto;
import com.itaekit.gateway.dto.account.request.EditAccountRequestDto;
import com.itaekit.gateway.dto.account.request.LoginAccountRequestDto;
import com.itaekit.gateway.dto.account.response.CreateAccountResponseDto;
import com.itaekit.gateway.dto.account.response.EditAccountResponseDto;
import com.itaekit.gateway.dto.account.response.LoginAccountResponseDto;
import com.itaekit.gateway.dto.user.UserDto;

public interface AccountService {
    CreateAccountResponseDto registerUser(CreateAccountRequestDto requestDto);
    EditAccountResponseDto editUser(EditAccountRequestDto requestDto);
    LoginAccountResponseDto findUserByUserId(LoginAccountRequestDto userId);
    UserDto getUserDetailsByUserId(String userId);
    void removeUser(String userId);
}
