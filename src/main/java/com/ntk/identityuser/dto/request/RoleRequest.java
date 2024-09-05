package com.ntk.identityuser.dto.request;

import static lombok.AccessLevel.PRIVATE;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class RoleRequest {

  String name;
  String description;
  Set<String> permittions;
}
