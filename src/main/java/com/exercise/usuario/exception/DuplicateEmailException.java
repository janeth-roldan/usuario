package com.exercise.usuario.exception;
public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException() {
        super("El correo ya fue registrado");
    }
}