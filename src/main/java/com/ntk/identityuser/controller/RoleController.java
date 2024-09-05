package com.ntk.identityuser.controller;

import com.ntk.identityuser.dto.request.RoleRequest;
import com.ntk.identityuser.dto.response.ApiResponse;
import com.ntk.identityuser.dto.response.RoleResponse;
import com.ntk.identityuser.service.RoleService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class RoleController {

  RoleService roleService;

  @PostMapping("/create")
  ApiResponse<RoleResponse> createRole(@RequestBody RoleRequest request) {
    RoleResponse role = roleService.create(request);
    return ApiResponse.<RoleResponse>builder()
        .status(201)
        .message("Role created successfully")
        .data(role)
        .build();
  }

  @GetMapping
  ApiResponse getAllRoles() {
    List<RoleResponse> roles = roleService.getAll();
    return ApiResponse.builder()
        .message("Success")
        .data(roles)
        .build();
  }

  @DeleteMapping("/delete")
  ApiResponse deleteRole(String name) {
    roleService.delete(name);
    return ApiResponse.builder()
        .message("Role " + name + "deleted successfully")
        .build();
  }
}
