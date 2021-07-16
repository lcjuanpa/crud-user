package com.c4.cruduser.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @NotNull
  @Column(nullable = false, unique = true, length = 20)
  @EqualsAndHashCode.Exclude private Long ci;
  
  @NotBlank
  @Column(nullable = false, length = 40)
  @EqualsAndHashCode.Exclude private String fullName;
}
