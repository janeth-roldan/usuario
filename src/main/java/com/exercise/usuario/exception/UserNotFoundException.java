package com.exercise.usuario.exception;

import java.util.UUID;
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UUID id) {
        super("El usuario con ID " + id + " no fue encontrado.");
    }
}
