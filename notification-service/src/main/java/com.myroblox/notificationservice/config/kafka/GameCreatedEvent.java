package com.myroblox.notificationservice.config.kafka;

import java.time.LocalDateTime;
import java.util.UUID;

public record GameCreatedEvent(
        UUID eventId,
        LocalDateTime occurredAt,
        Integer userId,
        String userName,
        Integer gameId,
        String gameName
) {
}

