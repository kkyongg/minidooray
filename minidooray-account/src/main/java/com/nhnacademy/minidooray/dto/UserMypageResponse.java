package com.nhnacademy.minidooray.dto;

import com.nhnacademy.minidooray.entity.User;
import com.nhnacademy.minidooray.entity.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserMypageResponse {
    private Long id;
    private String userId;
    private String email;
    private String password;
    private LocalDateTime lastLoginAt;
    private UserStatus status;

    public UserMypageResponse(User user) {
        this.id = user.getId();
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.lastLoginAt = user.getLastLoginAt();
        this.status = user.getStatus();
    }
}
