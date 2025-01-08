package com.sahelyfr.eataweekback.application;

import com.sahelyfr.eataweekback.application.dto.LoginUserDto;
import com.sahelyfr.eataweekback.application.dto.RegisterUserDto;
import com.sahelyfr.eataweekback.application.dto.LoginDto;
import com.sahelyfr.eataweekback.domain.enums.RoleEnum;
import com.sahelyfr.eataweekback.domain.service.AuthenticationService;
import com.sahelyfr.eataweekback.domain.service.JwtService;
import com.sahelyfr.eataweekback.infrastructure.entities.User;

import jakarta.annotation.security.RolesAllowed;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDto> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginDto loginResponse = new LoginDto(jwtToken, jwtService.getExpirationTime(), RoleEnum.USER);

        return ResponseEntity.ok(loginResponse);
    }

    @RolesAllowed("USER")
    @RequestMapping("/**")
    public String getUser() {
        return "Welcome User";
    }

    @RolesAllowed({ "USER", "ADMIN" })
    @RequestMapping("/admin")
    public String getAdmin() {
        return "Welcome Admin";
    }

}
