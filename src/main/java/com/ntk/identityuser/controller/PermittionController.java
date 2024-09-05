package com.ntk.identityuser.controller;

import com.ntk.identityuser.dto.request.PermittionRequest;
import com.ntk.identityuser.dto.response.ApiResponse;
import com.ntk.identityuser.dto.response.PermittionResponse;
import com.ntk.identityuser.service.PermittionService;
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
@RequestMapping("/permittions")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class PermittionController {

  PermittionService permittionService;

  @PostMapping("/create")
  ApiResponse<PermittionResponse> createPermittion(@RequestBody PermittionRequest request) {
    PermittionResponse permittion = permittionService.create(request);
    return ApiResponse.<PermittionResponse>builder()
        .status(201)
        .message("Permittion created successfully")
        .data(permittion)
        .build();
  }

  @GetMapping
  ApiResponse getAllPermittions() {
    List<PermittionResponse> permittions = permittionService.getAll();
    return ApiResponse.builder()
        .message("Success")
        .data(permittions)
        .build();
  }

  @DeleteMapping("/delete")
  ApiResponse deletePermittion(String name) {
    permittionService.delete(name);
    return ApiResponse.builder()
        .message("Permittion " + name + "deleted successfully")
        .build();
  }
}
