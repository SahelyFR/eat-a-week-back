package com.sahelyfr.eataweekback.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahelyfr.eataweekback.model.User;
import com.sahelyfr.eataweekback.service.UserService;

@RequestMapping("/users")
@RestController
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/me")
  public ResponseEntity<User> authenticatedUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    User currentUser = (User) authentication.getPrincipal();

    return ResponseEntity.ok(currentUser);
  }

  @GetMapping("/")
  public ResponseEntity<List<User>> allUsers() {
    List<User> users = userService.allUsers();
    System.out.println(users);
    return ResponseEntity.ok(users);
  }

}
