package com.myroblox.notificationservice.config.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class GameCreatedEventConsumer {

    public GameCreatedEventConsumer() {
        System.out.println("GameCreatedEventConsumer carregado");
    }

    @KafkaListener(
            topics = "game-created",
            groupId = "notification-service"
    )

    public void consume(GameCreatedEvent event) {
        System.out.println("Evento recebido: Game " + event.gameName() + " criado!");
    }
}