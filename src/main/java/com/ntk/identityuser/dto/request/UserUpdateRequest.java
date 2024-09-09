package com.ntk.identityuser.dto.request;

import com.ntk.identityuser.validator.DobConstrainst;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class UserUpdateRequest {

  @Email(message = "INVALID_EMAIL")
  String email;
  @Size(min = 10, message = "SIZE_PHONE")
  String phone;
  String address;
  String firstName;
  String lastName;
  @DobConstrainst(min = 18, message = "INVALID_DATE_OF_BIRTH")
  Integer age;

  List<String> roles;
}
