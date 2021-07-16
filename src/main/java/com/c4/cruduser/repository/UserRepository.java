package com.c4.cruduser.repository;

import com.c4.cruduser.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
  
}
