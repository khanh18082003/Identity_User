package com.ntk.identityuser.mapper;

import com.ntk.identityuser.dto.request.UserCreationRequest;
import com.ntk.identityuser.dto.request.UserUpdateRequest;
import com.ntk.identityuser.dto.response.UserResponse;
import com.ntk.identityuser.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);
    void update(UserUpdateRequest request, @MappingTarget User user);
}
