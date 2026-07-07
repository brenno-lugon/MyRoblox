package com.myroblox.gameservice.client;

import com.myroblox.gameservice.dto.UserResponse;
import com.myroblox.gameservice.exception.UserServiceException;
import feign.FeignException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class UserServiceClientFallbackFactory
        implements FallbackFactory<UserServiceClient> {

    @Override
    public UserServiceClient create(Throwable cause) {
        return username -> {
            if (cause instanceof UserServiceException ex) {
                throw ex;
            }

            throw new UserServiceException(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    "User Service is unavailable. Could not validate creator username."
            );
        };
    }
}
