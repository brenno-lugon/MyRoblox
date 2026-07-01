package com.myroblox.userservice.mapper;

import com.myroblox.userservice.dto.UserRequest;
import com.myroblox.userservice.dto.UserResponse;
import com.myroblox.userservice.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRequest request);

    UserResponse toResponse(User user);

}