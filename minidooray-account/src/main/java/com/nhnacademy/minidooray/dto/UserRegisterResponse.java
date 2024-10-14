package com.nhnacademy.minidooray.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
public class UserRegisterResponse {

    @NotNull
    @Length(max = 50)
    private String userId;

}
