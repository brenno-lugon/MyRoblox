package com.myroblox.gameservice.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    public static final String GAME_CREATED = "game-created";

    @Bean
    public NewTopic createdGamesTopic() {
        return TopicBuilder.name(GAME_CREATED)
                .partitions(1)
                .replicas(1)
                .build();
    }
}