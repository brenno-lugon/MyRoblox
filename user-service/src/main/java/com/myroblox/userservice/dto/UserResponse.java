package com.myroblox.userservice.dto;

import java.time.LocalDateTime;

public record UserResponse(Integer id,
                           String username,
                           String email,
                           String displayName,
                           String country,
                           Integer userLevel,
                           Integer experience,
                           Double robux,
                           LocalDateTime createdAt,
                           LocalDateTime updatedAt) {
}
