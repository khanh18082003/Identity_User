package com.ntk.identityuser.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Builder
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  String id;
  String username;
  String password;
  String email;
  String phone;
  String address;
  String firstName;
  String lastName;
  Integer age;

  @ManyToMany
  Set<Role> roles;
}
