package com.itaekit.gateway.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String userId;
    private String password;
    private String email;
    private String lastLoginAt;
    private String status;
}
