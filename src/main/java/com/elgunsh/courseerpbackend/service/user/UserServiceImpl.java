package com.elgunsh.courseerpbackend.service.user;

import com.elgunsh.courseerpbackend.model.mybatis.user.User;
import com.elgunsh.courseerpbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () ->  new RuntimeException("User not found!")
        );
    }

    @Override
    public void insert(User user) {
        userRepository.insert(user);
    }
}
