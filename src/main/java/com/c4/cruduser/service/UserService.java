package com.c4.cruduser.service;

import com.c4.cruduser.domain.User;
import java.util.List;

public interface UserService {
  public User findById(Long id);
  
  public List<User> findAll();

  public User create(User user);
  
  public User update(User user, Long id);
  
  public void deleteById(Long id);
}
