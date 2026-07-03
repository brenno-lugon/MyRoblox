package com.myroblox.gameservice.mapper;

import com.myroblox.gameservice.dto.GameRequest;
import com.myroblox.gameservice.dto.GameResponse;
import com.myroblox.gameservice.model.Game;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameMapper {

    Game toEntity(GameRequest request);

    GameResponse toResponse(Game game);

}