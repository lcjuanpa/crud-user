package com.c4.cruduser.controller;

import com.c4.cruduser.domain.User;
import com.c4.cruduser.service.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
  
  @GetMapping("/signup")
  public String showSignUpForm(User user) {
    return "add-user";
  }

  @PostMapping("/adduser")
  public String addUser(@Valid User user, BindingResult result, Model model) {
    if (result.hasErrors()) {
      return "add-user";
    }
    userService.create(user);
    return "redirect:/users";
  }
  
  @GetMapping("/edit/{id}")
  public String showUpdateForm(@PathVariable("id") long id, Model model) {
    User user = userService.findById(id);
    model.addAttribute("user", user);
    return "update-user";
  }
  
  @PostMapping("/update/{id}")
  public String updateUser(@PathVariable("id") long id, @Valid User user,
          BindingResult result, Model model) {
    if (result.hasErrors()) {
      user.setId(id);
      return "update-user";
    }
    userService.update(user, id);
    return "redirect:/users";
  }

  @GetMapping("/delete/{id}")
  public String deleteUser(@PathVariable("id") long id, Model model) {
    userService.deleteById(id);
    return "redirect:/users";
  }
}
