package com.ntk.identityuser.service;

import com.ntk.identityuser.dao.IUserRepository;
import com.ntk.identityuser.dao.RoleRepository;
import com.ntk.identityuser.dto.request.UserCreationRequest;
import com.ntk.identityuser.dto.request.UserUpdateRequest;
import com.ntk.identityuser.dto.response.RoleResponse;
import com.ntk.identityuser.dto.response.UserResponse;
import com.ntk.identityuser.entity.User;
import com.ntk.identityuser.enums.Roles;
import com.ntk.identityuser.exception.AppException;
import com.ntk.identityuser.exception.ErrorCode;
import com.ntk.identityuser.mapper.PermittionMapper;
import com.ntk.identityuser.mapper.RoleMapper;
import com.ntk.identityuser.mapper.UserMapper;
import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class UserService {

  RoleRepository roleRepository;
  IUserRepository userRepository;
  UserMapper userMapper;
  RoleMapper roleMapper;
  PermittionMapper permittionMapper;
  PasswordEncoder passwordEncoder;

  public UserResponse createUser(UserCreationRequest request) {
    if (userRepository.existsByUsername(request.getUsername())) {
      throw new AppException(ErrorCode.USERNAME_ALREADY_EXISTS);
    }
    // Map request to user entity
    User user = userMapper.toUser(request);
    // Encode password

    user.setPassword(passwordEncoder.encode(request.getPassword()));
    // set roles
    var roles = roleRepository.findAllById(List.of(Roles.USER.name()));
    user.setRoles(new HashSet<>(roles));

    return userMapper.toUserResponse(userRepository.save(user));
  }

  @PostAuthorize("returnObject.username == authentication.name")
  public UserResponse updateUser(UserUpdateRequest request, String userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    var roles = roleRepository.findAllById(request.getRoles());
    user.setRoles(new HashSet<>(roles));
    userMapper.update(request, user);
    return userMapper.toUserResponse(userRepository.save(user));
  }

  @PostAuthorize("returnObject.username == authentication.name")
  // Check if the returned object is the same as the authenticated user
  public UserResponse getUserById(String userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    return userMapper.toUserResponse(user);
  }

  @PreAuthorize("hasRole('ADMIN')")
  public List<UserResponse> getAllUsers() {
    return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
  }

  public UserResponse getMyInfo() {
    var context = SecurityContextHolder.getContext();
    String name = context.getAuthentication().getName();
    User user = userRepository.findByUsername(name)
        .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    return userMapper.toUserResponse(user);
  }
}
