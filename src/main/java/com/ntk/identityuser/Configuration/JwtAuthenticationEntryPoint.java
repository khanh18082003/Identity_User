package com.ntk.identityuser.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntk.identityuser.dto.response.ApiResponse;
import com.ntk.identityuser.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException {
    ErrorCode errorCode = ErrorCode.UNAUTHENTICATED;

    response.setStatus(errorCode.getStatusCode().value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

    ApiResponse apiResponse = ApiResponse.builder()
        .status(errorCode.getStatusCode().value())
        .message(errorCode.getMessage())
        .build();

    ObjectMapper objectMapper = new ObjectMapper();
    response.getWriter().write(objectMapper.writeValueAsString((apiResponse)));
    response.flushBuffer();
  }

}
