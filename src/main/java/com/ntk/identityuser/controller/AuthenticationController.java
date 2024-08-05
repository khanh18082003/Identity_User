package com.ntk.identityuser.controller;

import com.ntk.identityuser.dto.request.AuthenticationRequest;
import com.ntk.identityuser.dto.request.IntrospectRequest;
import com.ntk.identityuser.dto.response.ApiResponse;
import com.ntk.identityuser.dto.response.AuthenticationResponse;
import com.ntk.identityuser.dto.response.IntrospectResponse;
import com.ntk.identityuser.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-auth")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest request) {
        AuthenticationResponse response = authenticationService.isAuthenticated(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .status(response.isAuthenticated() ? 200 : 401)
                .message(response.isAuthenticated() ? "Login successful" : "Login failed")
                .data(response)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest introspectRequest) {
        return ApiResponse.<IntrospectResponse>builder()
                .message("Introspection successful")
                .data(authenticationService.introspect(introspectRequest))
                .build();
    }
}
