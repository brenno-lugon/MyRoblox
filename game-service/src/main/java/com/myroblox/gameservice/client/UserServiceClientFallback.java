package com.myroblox.gameservice.client;

import com.myroblox.gameservice.dto.UserResponse;
import com.myroblox.gameservice.exception.UserServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class UserServiceClientFallback implements UserServiceClient {

    @Override
    public UserResponse findByUsername(String username) {
        throw new UserServiceException(
                HttpStatus.SERVICE_UNAVAILABLE,
                "User Service is unavailable. Could not validate creator username."
        );
    }
}
