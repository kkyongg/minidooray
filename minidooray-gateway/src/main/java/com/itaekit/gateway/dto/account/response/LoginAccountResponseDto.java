package com.itaekit.gateway.dto.account.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginAccountResponseDto {
    private String userId;
    private String password;
}
