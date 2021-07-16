package com.c4.cruduser.service;

import com.c4.cruduser.domain.User;
import com.c4.cruduser.repository.UserRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
  private final UserRepository repository;
//  private final PasswordEncoder passwordEncoder;

  public UserServiceImpl(UserRepository repository/*, PasswordEncoder passwordEncoder*/) {
    this.repository = repository;
//    this.passwordEncoder = passwordEncoder;
  }
  
  
  @Override
  public User findById(Long id) {
    return repository.findById(id).orElseThrow(
        () -> new RuntimeException(String.format("%s %d", "Usuario no encontrado", id)));
  }

  @Override
  public List<User> findAll() {
    Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());
    return repository.findAll(pageable).toList();
  }

  @Override
  public User create(User user) {
    user.setId(0L);
    user.setPassword(user.getPassword()); // encrypt this password
    User savedUser = repository.save(user);
    log.info(String.format("%s (%d, %s)", "Usuario nuevo creado", 
        savedUser.getId(), savedUser.getEmail()));
    return savedUser;
  }

  @Override
  public User update(User newUser, Long id) {
    // Update user info only. Change password requires another specific service.
    User user = findById(id);
    user.setEmail(newUser.getEmail());
    User savedUser = repository.save(user);
    log.info(String.format("%s (%d, %s)", "Informacion de Usuario actualizado", savedUser.getId(),
        savedUser.getEmail()));
    return savedUser;
  }

  @Override
  public void deleteById(Long id) {
    try {
      User user = findById(id);
      repository.deleteById(id);
      log.info(String.format("%s (%d)", "Usuario eliminado", id));
    } catch (EmptyResultDataAccessException ex) {
      log.warn(String.format("%s. %s (%d) -> %s", "Error al eliminar el registro (usuario)",
          "Usuario no encontrado", id, ex.toString()));
      throw new RuntimeException(String.format("%s. %s %d",
          "Error al eliminar el usuario", "Usuario no encontrado", id));
    }
  }
  
}
