package com.ntk.identityuser.service;

import com.ntk.identityuser.dao.PermittionRepository;
import com.ntk.identityuser.dao.RoleRepository;
import com.ntk.identityuser.dto.request.RoleRequest;
import com.ntk.identityuser.dto.response.RoleResponse;
import com.ntk.identityuser.entity.Role;
import com.ntk.identityuser.mapper.RoleMapper;
import java.util.HashSet;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class RoleService {

  RoleRepository roleRepository;
  PermittionRepository permittionRepository;
  RoleMapper roleMapper;

  public RoleResponse create(RoleRequest request) {
    Role role = roleMapper.toRole(request);
    var permittions = permittionRepository.findAllById(request.getPermittions());
    role.setPermittions(new HashSet<>(permittions));
    return roleMapper.toRoleResponse(roleRepository.save(role));
  }

  public List<RoleResponse> getAll() {
    return roleRepository.findAll().stream()
        .map(roleMapper::toRoleResponse)
        .toList();
  }

  public void delete(String name) {
    roleRepository.deleteById(name);
  }
}
