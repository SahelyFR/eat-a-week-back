package com.sahelyfr.eataweekback.controller;

import java.util.Collections;
import java.util.Map;

import com.sahelyfr.eataweekback.dto.LoginUserDto;
import com.sahelyfr.eataweekback.dto.RegisterUserDto;
import com.sahelyfr.eataweekback.model.User;
import com.sahelyfr.eataweekback.responses.LoginResponse;
import com.sahelyfr.eataweekback.service.AuthenticationService;
import com.sahelyfr.eataweekback.service.JwtService;

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
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken)
                .setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

    /*
     * 
     * 
     * @RolesAllowed("USER")
     * 
     * @RequestMapping("/**")
     * public String getUser() {
     * return "Welcome User";
     * }
     * 
     * @RolesAllowed({ "USER", "ADMIN" })
     * 
     * @RequestMapping("/admin")
     * public String getAdmin() {
     * return "Welcome Admin";
     * }
     * 
     * @GetMapping("/user")
     * public String getUserInfo(Principal user) {
     * StringBuffer userInfo = new StringBuffer();
     * if (user instanceof UsernamePasswordAuthenticationToken) {
     * userInfo.append(getUserNamePasswordLoginInfo(user));
     * }
     *//*
        * else if(user instanceof OAuth2AuthenticationToken){
        * userInfo.append(getOAuth2LoginInfo(user));
        * }
        *//*
           * return userInfo.toString();
           * }
           * 
           * @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
           * public User registerUser(@RequestBody String payload) throws
           * JsonMappingException, JsonProcessingException {
           * 
           * ObjectMapper mapper = new ObjectMapper();
           * User user = mapper.readValue(payload, User.class);
           * 
           * System.out.printf("Registering user: %s", user.getUsername());
           * 
           * if (user.getUsername() != null && user.getPassword() != null) {
           * String password = new String(
           * Base64.getDecoder().decode(
           * user.getPassword().getBytes(StandardCharsets.UTF_8)));
           * user.setPassword(passwordEncoder.encode(password));
           * User u = userRepo.save(user);
           * Authorities r = new Authorities(u.getUsername(), "USER");
           * roleRepo.save(r);
           * System.out.printf("Registering for user: %s is OK\\n", user.getUsername());
           * return u;
           * } else {
           * System.out.printf("Registering for user: %s is KO\\n", user.getUsername());
           * return null;
           * }
           * }
           * 
           * private StringBuffer getUserNamePasswordLoginInfo(Principal user) {
           * StringBuffer userNameInfo = new StringBuffer();
           * 
           * UsernamePasswordAuthenticationToken token =
           * ((UsernamePasswordAuthenticationToken) user);
           * if (token.isAuthenticated()) {
           * User u = (User) token.getPrincipal();
           * userNameInfo.append("Welcome, " + u.getUsername());
           * } else {
           * userNameInfo.append("NA");
           * }
           * return userNameInfo;
           * }
           */
}
