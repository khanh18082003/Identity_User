package com.ntk.identityuser.exception;

import com.ntk.identityuser.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class GlobalException {

  @ExceptionHandler(value = AppException.class)
  public ResponseEntity<ApiResponse> handleAppException(AppException e) {
    return ResponseEntity.badRequest().body(ApiResponse.builder()
        .status(e.getErrorCode().getCode())
        .message(e.getErrorCode().getMessage())
        .build());
  }

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException e) {
    return ResponseEntity.badRequest().body(ApiResponse.builder()
        .status(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode())
        .message(e.getMessage())
        .build());
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse> handleValidationException(MethodArgumentNotValidException e) {
    String enumKey = Objects.requireNonNull(e.getFieldError()).getDefaultMessage();
    ErrorCode errorCode;
    try {
      errorCode = ErrorCode.valueOf(enumKey);
    } catch (IllegalArgumentException exception) {
      errorCode = ErrorCode.INVALID_KEY;
    }

    ApiResponse response = ApiResponse.builder()
        .status(errorCode.getCode())
        .message(errorCode.getMessage())
        .build();
    return ResponseEntity.badRequest().body(response);
  }
}
