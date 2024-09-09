package com.ntk.identityuser.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
  UNCATEGORIZED_EXCEPTION(0, "Uncategorized", HttpStatus.INTERNAL_SERVER_ERROR),
  INVALID_KEY(400, "Invalid key", HttpStatus.BAD_REQUEST),
  USER_NOT_FOUND(404, "User not found", HttpStatus.NOT_FOUND),
  USERNAME_ALREADY_EXISTS(400, "Username already exists", HttpStatus.FOUND),
  INVALID_REQUEST(400, "Invalid request", HttpStatus.BAD_REQUEST),
  INTERNAL_SERVER_ERROR(500, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
  UNAUTHORIZED(403, "You do not have permittion", HttpStatus.FORBIDDEN),
  UNAUTHENTICATED(401, "Unauthenticated", HttpStatus.UNAUTHORIZED),
  SIZE_PASSWORD(400, "Password must be between {min} and {max} characters", HttpStatus.BAD_REQUEST),
  INVALID_EMAIL(400, "Invalid email", HttpStatus.BAD_REQUEST),
  SIZE_PHONE(400, "Phone number must be at least {min} characters", HttpStatus.BAD_REQUEST),
  ROLE_NOT_FOUND(404, "Role not found", HttpStatus.NOT_FOUND),
  INVALID_DATE_OF_BIRTH(400, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
  ;

  private int code;
  private String message;
  private HttpStatusCode statusCode;

  ErrorCode(int code, String message, HttpStatusCode statusCode) {
    this.code = code;
    this.message = message;
    this.statusCode = statusCode;
  }

}
