package com.nhnacademy.minidooray.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Table(name = "users")
public class User {
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

    private LocalDateTime lastLoginAt;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;

    public User (String userId, String password, String email) {
        this.userId = userId;
        this.password = password;
        this.email = email;
    }
}
