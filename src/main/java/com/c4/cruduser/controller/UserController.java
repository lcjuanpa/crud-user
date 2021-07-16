package com.c4.cruduser.controller;

import com.c4.cruduser.domain.User;
import com.c4.cruduser.service.UserService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }
  
  @GetMapping(path = "/users")
  public String getAllUsers(Model model) {
    List<User> userList = userService.findAll();
    model.addAttribute("userList", userList);
    return "users";
  }
  
  @GetMapping(path = "/{id}")
  public String getCarById(@PathVariable Long id, Model model) {
    User user = userService.findById(id);
    model.addAttribute("user", user);
    return "user";
  }
}
