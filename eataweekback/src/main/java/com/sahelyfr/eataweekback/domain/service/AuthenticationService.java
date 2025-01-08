package com.sahelyfr.eataweekback.domain.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sahelyfr.eataweekback.application.dto.LoginUserDto;
import com.sahelyfr.eataweekback.application.dto.RegisterUserDto;
import com.sahelyfr.eataweekback.domain.enums.RoleEnum;
import com.sahelyfr.eataweekback.infrastructure.RoleRepository;
import com.sahelyfr.eataweekback.infrastructure.UserRepository;
import com.sahelyfr.eataweekback.infrastructure.entities.Role;
import com.sahelyfr.eataweekback.infrastructure.entities.User;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public User signup(RegisterUserDto input) {
        Role userRole = roleRepository.findByName(RoleEnum.USER);

        User user = new User()
                .setUsername(input.username())
                .setEmail(input.email())
                .setPassword(passwordEncoder.encode(input.password()))
                .setRole(input.role() != null ? input.role() : userRole);

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.email(),
                        input.password()));

        return userRepository.findByEmail(input.email())
                .orElseThrow();
    }

}