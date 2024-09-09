package com.ntk.identityuser.mapper;

import com.ntk.identityuser.dto.request.RoleRequest;
import com.ntk.identityuser.dto.response.RoleResponse;
import com.ntk.identityuser.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

  @Mapping(target = "permittions", ignore = true)
  Role toRole(RoleRequest roleRequest);
  
  RoleResponse toRoleResponse(Role role);
}
