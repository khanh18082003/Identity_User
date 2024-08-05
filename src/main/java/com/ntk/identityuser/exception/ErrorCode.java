package com.ntk.identityuser.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(0, "Uncategorized"),
    INVALID_KEY(400, "Invalid key"),
    USER_NOT_FOUND(404, "User not found"),
    USERNAME_ALREADY_EXISTS(400, "Username already exists"),
    INVALID_REQUEST(400, "Invalid request"),
    INTERNAL_SERVER_ERROR(500, "Internal server error"),
    UNAUTHORIZED(401, "Unauthorized"),
    SIZE_PASSWORD(400, "Password must be between 8 and 20 characters"),
    INVALID_EMAIL(400, "Invalid email"),
    SIZE_PHONE(400, "Phone number must be at least 10 characters");

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
