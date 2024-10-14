package com.nhnacademy.minidooray.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {

    @NotNull
    @Length(max = 50)
    private String userId;

    @NotNull
    @Length(max = 200)
    private String password;

    @NotNull
    @Length(max = 50)
    private String email;


}
