package com.myroblox.gameservice.client;

import com.myroblox.gameservice.dto.UserResponse;
import com.myroblox.gameservice.exception.UserServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class UserServiceClient {

    private final RestClient restClient;

    public UserServiceClient(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("http://localhost:8082")
                .build();
    }

    public UserResponse findByUsername(String username) {
        try {
            return restClient.get()
                    .uri("/user/search?username={username}", username)
                    .retrieve()
                    .onStatus(status -> status.value() == 404,
                            (request, response) -> {
                                throw new UserServiceException(
                                        HttpStatus.NOT_FOUND,
                                        "User '" + username + "' not found."
                                );
                            })
                    .body(UserResponse.class);
        } catch (UserServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new UserServiceException(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    "User Service is unavailable."
            );
        }
    }
}