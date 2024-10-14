package com.nhnacademy.minidooray.dto;

import com.nhnacademy.minidooray.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponse {

    @NotNull
    @Length(max = 50)
    private String userId;

    @NotNull
    @Length(max = 200)
    private String password;

    public UserLoginResponse(User user) {
        this.userId = user.getUserId();
        this.password = user.getPassword();
    }
}
