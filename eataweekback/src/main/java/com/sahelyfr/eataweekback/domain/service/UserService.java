package com.sahelyfr.eataweekback.domain.service;

import com.sahelyfr.eataweekback.infrastructure.UserRepository;
import com.sahelyfr.eataweekback.infrastructure.entities.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }
}
