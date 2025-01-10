package Finance.demo.controller;

import Finance.demo.models.User;
import Finance.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public User register(@RequestBody User user) {
    return userService.registerUser(user);
  }

  @PostMapping("/login")
  public User login(@RequestParam String email, @RequestParam String password) {
    return userService.loginUser(email, password);
  }
}
