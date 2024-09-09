package com.ntk.identityuser.controller;

import com.ntk.identityuser.dto.request.UserCreationRequest;
import com.ntk.identityuser.dto.request.UserUpdateRequest;
import com.ntk.identityuser.dto.response.ApiResponse;
import com.ntk.identityuser.dto.response.UserResponse;
import com.ntk.identityuser.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-users")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class UserController {

  UserService userService;

  @PostMapping("/register")
  ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
    UserResponse user = userService.createUser(request);
    return ApiResponse.<UserResponse>builder()
        .status(201)
        .message("User created successfully")
        .data(user)
        .build();
  }

  @PutMapping("/update/{userId}")
  ApiResponse<UserResponse> updateUser(@RequestBody @Valid UserUpdateRequest request,
      @PathVariable("userId") String userId) {
    UserResponse user = userService.updateUser(request, userId);
    return ApiResponse.<UserResponse>builder()
        .message("User updated successfully")
        .data(user)
        .build();
  }

  @GetMapping("/get/{userId}")
  ApiResponse<UserResponse> getUser(@PathVariable String userId) {
    UserResponse user = userService.getUserById(userId);
    return ApiResponse.<UserResponse>builder()
        .message("Success")
        .data(user)
        .build();
  }

  @GetMapping
  ApiResponse getAllUsers() {
    var authentication = SecurityContextHolder.getContext().getAuthentication();
    log.info("User: {}", authentication.getName());
    authentication.getAuthorities().forEach(authority -> log.info("Authority: {}", authority));

    return ApiResponse.builder()
        .status(200)
        .message("Success")
        .data(userService.getAllUsers())
        .build();
  }

  @GetMapping("/my-info")
  ApiResponse<UserResponse> getMyInfo() {
    UserResponse user = userService.getMyInfo();
    return ApiResponse.<UserResponse>builder()
        .status(200)
        .message("Success")
        .data(user)
        .build();
  }
}
