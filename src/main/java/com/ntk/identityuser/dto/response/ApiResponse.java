package com.ntk.identityuser.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // Ignore null fields
public class ApiResponse<E> {
    int status = 200;
    String message;
    E data;
}
