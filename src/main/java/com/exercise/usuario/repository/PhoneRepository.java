package com.exercise.usuario.repository;

import com.exercise.usuario.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhoneRepository extends JpaRepository<Phone, UUID> {

}
