package com.nhnacademy.minidooray.repository;

import com.nhnacademy.minidooray.entity.User;
import com.nhnacademy.minidooray.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUserId(String userId);
    List<User> findAllByLastLoginAtBeforeAndStatusEquals(LocalDateTime localDateTime, UserStatus status);
}
