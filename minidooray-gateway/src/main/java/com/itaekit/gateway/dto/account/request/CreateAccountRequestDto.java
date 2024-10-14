package com.itaekit.gateway.dto.account.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CreateAccountRequestDto {
    private String userId;
    private String email;
    private String password;
}
