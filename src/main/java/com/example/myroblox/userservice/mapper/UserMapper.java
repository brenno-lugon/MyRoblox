package com.example.myroblox.userservice.mapper;

import com.example.myroblox.userservice.dto.UserRequest;
import com.example.myroblox.userservice.dto.UserResponse;
import com.example.myroblox.userservice.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRequest request);

    UserResponse toResponse(User user);

}