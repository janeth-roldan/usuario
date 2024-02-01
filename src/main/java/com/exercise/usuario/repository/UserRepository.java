package com.exercise.usuario.repository;

import com.exercise.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Usuario, UUID> {

    boolean existsByEmail(String email);
    Optional<Usuario>  findByNameAndPassword(String name, String password);

}
