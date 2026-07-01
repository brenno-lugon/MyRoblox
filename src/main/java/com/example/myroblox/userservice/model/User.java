package com.example.myroblox.userservice.model;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String displayName;

    private String country;
    private Integer userLevel;
    private Integer experience;
    private Double robux;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;
    
}
