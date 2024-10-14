package com.nhnacademy.minidooray.dto;

import com.nhnacademy.minidooray.entity.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
public class UserEditRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(max = 50)
    private String userId;

    @NotNull
    @Email
    @Length(max = 50)
    private String email;

    @NotNull
    @Length(max = 200)
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;
}
