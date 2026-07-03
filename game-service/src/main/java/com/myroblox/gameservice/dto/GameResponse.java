package com.myroblox.gameservice.dto;

import java.time.LocalDateTime;

public record GameResponse(Integer id,
                           String name,
                           String description,
                           String genre,
                           String creatorUsername,
                           Integer maxPlayers,
                           Integer visits,
                           Double likes,
                           LocalDateTime createdAt,
                           LocalDateTime updatedAt) {
}
