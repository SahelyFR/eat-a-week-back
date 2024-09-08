package com.sahelyfr.eataweekback.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sahelyfr.eataweekback.dto.LoginUserDto;
import com.sahelyfr.eataweekback.dto.RegisterUserDto;
import com.sahelyfr.eataweekback.model.User;
import com.sahelyfr.eataweekback.repository.UserRepository;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        User user = new User()
                .setUsername(input.username())
                .setEmail(input.email())
                .setPassword(passwordEncoder.encode(input.password()));

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        System.out.println(input.email());
        System.out.println(input.password());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.email(),
                        input.password()));

        return userRepository.findByEmail(input.email())
                .orElseThrow();
    }

}