package com.itaekit.gateway.dto.account.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EditAccountResponseDto {
    private Long id;
    private String userId;
    private String email;
    private String password;
    private String status;
}
