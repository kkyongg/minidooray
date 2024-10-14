package com.nhnacademy.minidooray.dto;

import com.nhnacademy.minidooray.entity.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserEditResponse {

    private Long id;
    private String userId;
    @Setter
    private String email;
    @Setter
    private String password;
    private UserStatus status;

}
