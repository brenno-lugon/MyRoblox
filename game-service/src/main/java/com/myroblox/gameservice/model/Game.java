package com.myroblox.gameservice.model;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Table(name = "tb_game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = false)
    private String name;
    private String description;
    private String genre;
    private String creatorUsername;
    @Column(nullable = false, unique = false)
    private Integer maxPlayers;
    private Integer visits;
    private Integer likes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
