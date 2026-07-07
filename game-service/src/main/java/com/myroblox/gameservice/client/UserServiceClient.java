package com.myroblox.gameservice.client;

import com.myroblox.gameservice.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "user-service",
        url = "${myroblox.user-service.url}",
        fallbackFactory = UserServiceClientFallbackFactory.class
)
public interface UserServiceClient {

    @GetMapping("/user/search")
    UserResponse findByUsername(@RequestParam String username);

}