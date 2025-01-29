package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<User> registerUser(@RequestBody User user) {
    return ResponseEntity.ok(userService.registerUser(user));
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> loginUser(@PathVariable Long id) {
    return ResponseEntity.ok(userService.getUserById(id));
  }
}
