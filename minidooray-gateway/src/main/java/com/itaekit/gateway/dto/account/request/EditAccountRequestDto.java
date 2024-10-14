package com.itaekit.gateway.dto.account.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditAccountRequestDto {
    private Long id;
    private String userId;
    private String email;
    private String password;
    private String status;
}
