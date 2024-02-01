package com.exercise.usuario.service;

import com.exercise.usuario.model.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<Usuario> getAllUsers();
    Optional<Usuario> getUserById(UUID id);
    Usuario saveUser(Usuario usuario);
    Optional<Usuario> findByNameAndPassword(String name, String password);
    void deleteUser(UUID id);

}