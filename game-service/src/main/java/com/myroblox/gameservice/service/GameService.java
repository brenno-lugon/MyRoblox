package com.myroblox.gameservice.service;

import com.myroblox.gameservice.client.UserServiceClient;
import com.myroblox.gameservice.config.kafka.GameCreatedEvent;
import com.myroblox.gameservice.config.kafka.GameCreatedEventPublisher;
import com.myroblox.gameservice.dto.UserResponse;
import com.myroblox.gameservice.model.Game;
import com.myroblox.gameservice.repository.GameRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final UserServiceClient userServiceClient;
    private final GameCreatedEventPublisher gameCreatedEventPublisher;

    public GameService(GameRepository gameRepository, UserServiceClient userServiceClient, GameCreatedEventPublisher gameCreatedEventPublisher) {
        this.gameRepository = gameRepository;
        this.userServiceClient = userServiceClient;
        this.gameCreatedEventPublisher = gameCreatedEventPublisher;
    }

    public Game create(Game game) {

        // VALIDAÇÃO PARA SABER SE O USUÁRIO EXISTE E SE O SERVIÇO ESTÁ ATIVO
        UserResponse userResponse = userServiceClient.findByUsername(game.getCreatorUsername());

        if (game.getVisits() == null) {
            game.setVisits(0);
        }

        if (game.getLikes() == null) {
            game.setLikes(0);
        }

        game.setCreatedAt(LocalDateTime.now());
        game.setUpdatedAt(LocalDateTime.now());

        Game created = gameRepository.save(game);

        GameCreatedEvent event = new GameCreatedEvent(
                UUID.randomUUID(),
                LocalDateTime.now(),
                userResponse.id(),
                userResponse.username(),
                created.getId(),
                created.getName()
        );

        gameCreatedEventPublisher.publish(event);

        return created;
    }

    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    public Game findById(Long id) {
        return gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game não encontrado"));
    }

    public Game findByName(String name) {
        return gameRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Game não encontrado."));
    }

    public Game update(Game game, Long id) {
        Game existingGame = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game não encontrado"));

        existingGame.setName(game.getName());
        existingGame.setDescription(game.getDescription());
        existingGame.setGenre(game.getGenre());
        existingGame.setCreatorUsername(game.getCreatorUsername());
        existingGame.setMaxPlayers(game.getMaxPlayers());
        existingGame.setUpdatedAt(LocalDateTime.now());


        return gameRepository.save(existingGame);
    }

    public void deleteById(Long id) {
        gameRepository.deleteById(id);
    }

}
