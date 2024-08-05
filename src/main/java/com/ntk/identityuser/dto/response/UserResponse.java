package com.ntk.identityuser.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

  String id;
  String username;
  String password;
  String email;
  String phone;
  String address;
  String firstName;
  String lastName;
  Integer age;
}
