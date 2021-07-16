package com.c4.cruduser;

import com.c4.cruduser.domain.User;
import com.c4.cruduser.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudUserApplication {

  public static void main(String[] args) {
    SpringApplication.run(CrudUserApplication.class, args);
  }

  @Bean
  public CommandLineRunner fillDataAtStart(UserRepository userRepository) {
    return (args) -> {
      // save few customers
      userRepository.save(new User("juan@gmail.com", "pass"));
      userRepository.save(new User("pablo@gmail.com", "clave"));
      userRepository.save(new User("ingrid@gmail.com", "amor"));
      userRepository.save(new User("gabriela@gmail.com", "hola"));
      userRepository.save(new User("otro@gmail.com", "otro"));
    };
  }
  
}
