package com.ntk.identityuser.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class UserCreationRequest {

  String username;
  @Size(min = 8, max = 20, message = "SIZE_PASSWORD")
  String password;
  @Email(message = "INVALID_EMAIL")
  String email;
  @Size(min = 10, message = "SIZE_PHONE")
  String phone;
  String address;
  String firstName;
  String lastName;
  Integer age;
}
