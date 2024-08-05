package com.ntk.identityuser.controller;

import com.ntk.identityuser.dto.request.UserCreationRequest;
import com.ntk.identityuser.dto.request.UserUpdateRequest;
import com.ntk.identityuser.dto.response.ApiResponse;
import com.ntk.identityuser.dto.response.UserResponse;
import com.ntk.identityuser.entity.User;
import com.ntk.identityuser.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-users")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class UserController {

  UserService userService;

  @PostMapping("/register")
  ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
    User user = userService.createUser(request);
    return ApiResponse.<User>builder()
        .status(201)
        .message("User created successfully")
        .data(user)
        .build();
  }

  @PutMapping("/update/{userId}")
  ApiResponse<UserResponse> updateUser(@RequestBody @Valid UserUpdateRequest request,
      @PathVariable String userId) {
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
    return ApiResponse.builder()
        .status(200)
        .message("Success")
        .data(userService.getAllUsers())
        .build();
  }
}
