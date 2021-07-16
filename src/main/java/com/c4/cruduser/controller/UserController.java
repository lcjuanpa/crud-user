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
    return "redirect:/index";
  }
  
  @PostMapping("/update/{id}")
  public String updateUser(@PathVariable("id") long id, @Valid User user,
          BindingResult result, Model model) {
    if (result.hasErrors()) {
      user.setId(id);
      return "update-user";
    }

    userService.update(user, id);
    return "redirect:/index";
  }

  @GetMapping("/delete/{id}")
  public String deleteUser(@PathVariable("id") long id, Model model) {
    userService.deleteById(id);
    return "redirect:/index";
  }
}
