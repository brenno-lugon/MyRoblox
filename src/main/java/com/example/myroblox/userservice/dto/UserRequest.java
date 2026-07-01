package com.example.myroblox.userservice.dto;

public record UserRequest(String username,
                          String email,
                          String displayName,
                          String country,
                          Integer userLevel,
                          Integer experience,
                          Double robux) {
}
