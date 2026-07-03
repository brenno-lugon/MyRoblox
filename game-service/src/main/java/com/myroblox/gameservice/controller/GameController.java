package com.myroblox.gameservice.controller;

import com.myroblox.gameservice.dto.GameRequest;
import com.myroblox.gameservice.dto.GameResponse;
import com.myroblox.gameservice.mapper.GameMapper;
import com.myroblox.gameservice.model.Game;
import com.myroblox.gameservice.service.GameService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    GameService service;
    GameMapper mapper;

    public GameController(GameService service,
                          GameMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GameResponse create(@Valid @RequestBody GameRequest gameRequest) {
        Game game = mapper.toEntity(gameRequest);
        Game createdGame = service.create(game);
        return mapper.toResponse(createdGame);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GameResponse> findAll() {
        List<Game> gameList = service.findAll();
        return gameList.stream().map(mapper::toResponse).toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GameResponse findById(@PathVariable Long id) {
        Game game = service.findById(id);
        return mapper.toResponse(game);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public GameResponse searchByName(@RequestParam String name) {
        Game game = service.findByName(name);
        return mapper.toResponse(game);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GameResponse findById(@RequestBody GameRequest gameRequest, @PathVariable Long id) {
        Game game = mapper.toEntity(gameRequest);
        Game gameUpdated = service.update(game, id);
        return mapper.toResponse(gameUpdated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

}
