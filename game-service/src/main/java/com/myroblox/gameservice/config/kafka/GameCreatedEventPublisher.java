package com.myroblox.gameservice.config.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.myroblox.gameservice.config.kafka.KafkaTopicConfig.GAME_CREATED;

@Component
public class GameCreatedEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public GameCreatedEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(GameCreatedEvent event) {
        kafkaTemplate.send(GAME_CREATED, event);
        System.out.println("Evento publicado: " + event);
    }
}
