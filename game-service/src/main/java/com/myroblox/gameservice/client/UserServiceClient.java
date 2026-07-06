package com.myroblox.gameservice.client;

import com.myroblox.gameservice.dto.UserResponse;
import com.myroblox.gameservice.exception.UserServiceException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClient;

@FeignClient(
        name = "user-service",
        url = "${myroblox.user-service.url}",
        fallback = UserServiceClientFallback.class
)
public interface UserServiceClient {

    @GetMapping("/user/search")
    UserResponse findByUsername(@RequestParam String username);

}