package com.myroblox.gameservice.service;

import com.myroblox.gameservice.client.UserServiceClient;
import com.myroblox.gameservice.dto.UserResponse;
import com.myroblox.gameservice.model.Game;
import com.myroblox.gameservice.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final UserServiceClient userServiceClient;

    public GameService(GameRepository gameRepository, UserServiceClient userServiceClient) {
        this.gameRepository = gameRepository;
        this.userServiceClient = userServiceClient;
    }

    public Game create(Game game) {

        // VALIDAÇÃO PARA SABER SE O USUÁRIO EXISTE E SE O SERVIÇO ESTÁ ATIVO
        userServiceClient.findByUsername(game.getCreatorUsername());

        if (game.getVisits() == null) {
            game.setVisits(0);
        }

        if (game.getLikes() == null) {
            game.setLikes(0);
        }

        game.setCreatedAt(LocalDateTime.now());
        game.setUpdatedAt(LocalDateTime.now());

        return gameRepository.save(game);
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
