package com.myroblox.gameservice.exception;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
public class UserServiceErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, Response response) {

        try {

            if (response.body() != null) {

                String body = Util.toString(response.body().asReader(StandardCharsets.UTF_8));

                Map<String, Object> error =
                        objectMapper.readValue(body, new TypeReference<>() {
                        });

                String message = (String) error.getOrDefault("message", "Unexpected error");

                return new UserServiceException(
                        HttpStatus.valueOf(response.status()),
                        message
                );
            }

        } catch (Exception ignored) {
        }

        return new UserServiceException(
                HttpStatus.valueOf(response.status()),
                "Error communicating with User Service."
        );
    }
}