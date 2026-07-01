package com.myroblox.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(@NotBlank String username,
                          @Email
                          @NotBlank String email,
                          @NotBlank String displayName,
                          @NotBlank String country,
                          Integer userLevel,
                          Integer experience,
                          Double robux) {
}
