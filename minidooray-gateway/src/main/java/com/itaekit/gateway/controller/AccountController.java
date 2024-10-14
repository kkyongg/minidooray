package com.itaekit.gateway.controller;

import com.itaekit.gateway.dto.account.request.CreateAccountRequestDto;
import com.itaekit.gateway.dto.account.request.EditAccountRequestDto;
import com.itaekit.gateway.dto.account.response.EditAccountResponseDto;
import com.itaekit.gateway.dto.user.UserDto;
import com.itaekit.gateway.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // 회원 가입
    @GetMapping("/signup")
    public String signup() {
        return "signupForm";
    }

    @PostMapping("/signup")
    public String doSignup(CreateAccountRequestDto requestDto) {
        accountService.registerUser(requestDto);
        return "redirect:/login";
    }

    // 로그인
    @GetMapping("/login")
    public String login() {
        return "loginForm";
    }

    // 회원 상세 조회
    @GetMapping("/user/{userId}")
    public String mypage(@PathVariable String userId, Model model) {
        UserDto userDto = accountService.getUserDetailsByUserId(userId);
        model.addAttribute("user", userDto);
        return "mypage";
    }

    // 회원 정보 변경
    @PostMapping("/edit/user")
    public String editUser(EditAccountRequestDto requestDto) {
        EditAccountResponseDto responseDto = accountService.editUser(requestDto);
        return "redirect:/";
    }

    // 회원 탈퇴
    @GetMapping("/user/resign/{userId}")
    public String removeUser(@PathVariable String userId) {
        accountService.removeUser(userId);
        return "redirect:/";
    }

}
