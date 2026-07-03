package com.myroblox.gameservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GameRequest(@NotBlank String name,
                          String description,
                          String genre,
                          String creatorUsername,
                          @NotNull Integer maxPlayers) {
}
