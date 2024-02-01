package com.exercise.usuario.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UserResponse {
    private UUID id;
    private LocalDateTime created;
    @JsonProperty("lastLogin")
    private LocalDateTime lastLogin;
    private String token;
    @JsonProperty("isActive")
    private boolean isActive;
}