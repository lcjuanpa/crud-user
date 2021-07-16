package com.c4.cruduser.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @NotBlank
  @Column(nullable = false, unique = true, length = 50)
  @EqualsAndHashCode.Exclude private String email;
  
  @NotBlank
  @Column(nullable = false, length = 32)
  @EqualsAndHashCode.Exclude private String password;
}
