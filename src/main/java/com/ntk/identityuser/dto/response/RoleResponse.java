package com.ntk.identityuser.dto.response;

import static lombok.AccessLevel.PRIVATE;

import com.ntk.identityuser.entity.Permittion;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Builder
public class RoleResponse {

  String name;
  String description;
  Set<PermittionResponse> permittions;
}
