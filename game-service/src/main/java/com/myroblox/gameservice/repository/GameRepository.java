package com.myroblox.gameservice.repository;

import com.myroblox.gameservice.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {

    Optional<Game> findByName(String name);
//    findAll()
//    findById(id)
//    save(user)
//    delete(user)
//    deleteById(id)
//    existsById(id)
//    count()

}
