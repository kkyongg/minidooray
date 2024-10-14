package com.nhnacademy.minidooray.service;

import com.nhnacademy.minidooray.dto.UserEditRequest;
import com.nhnacademy.minidooray.dto.UserRegisterRequest;
import com.nhnacademy.minidooray.entity.User;
import com.nhnacademy.minidooray.entity.UserStatus;
import com.nhnacademy.minidooray.exception.UserNotFoundException;
import com.nhnacademy.minidooray.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void registerUser(UserRegisterRequest request) {
        User savedUser = new User(request.getUserId(), request.getPassword(), request.getEmail());
        userRepository.save(savedUser);
    }

    @Override
    @Transactional
    public User doLogin(String userId) {
        User user = userRepository.findByUserId(userId);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException("user not found.");
        }

        if (user.getStatus().equals(UserStatus.RESIGNED)) {
            throw new UserNotFoundException("resigned user.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        user.setLastLoginAt(LocalDateTime.parse(formattedDateTime, formatter));

        return user;
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findByUserId(userId);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException("user not found.");
        }

        return user;
    }

    @Override
    @Transactional
    public void updateOldUsersToInactive() {
        List<User> users = userRepository.findAllByLastLoginAtBeforeAndStatusEquals(LocalDateTime.now().minusYears(1), UserStatus.ACTIVE);
        for (User user : users) {
            user.setStatus(UserStatus.INACTIVE);
        }
        userRepository.saveAll(users);
    }

    @Override
    public void editUser(String userId, UserEditRequest request) {
        User user = userRepository.findByUserId(userId);

        if (user.getStatus().equals(UserStatus.RESIGNED)) {
            throw new UserNotFoundException("resigned user.");
        }

        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        userRepository.save(user);

    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findByUserId(userId);

        if (Objects.isNull(user)) {
            throw new UserNotFoundException("user not found.");
        }
        user.setStatus(UserStatus.RESIGNED);
        userRepository.save(user);
    }
}
