package com.exercise.usuario.service;

import com.exercise.usuario.exception.DuplicateEmailException;
import com.exercise.usuario.jwt.JwtTokenProvider;
import com.exercise.usuario.model.Phone;
import com.exercise.usuario.model.Usuario;
import com.exercise.usuario.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.exercise.usuario.repository.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;

    private final JwtTokenProvider jwtTokenProvider;

    @Value("${user.password.regex}")
    private String regexPassword;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PhoneRepository phoneRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.phoneRepository = phoneRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    @Transactional
    public List<Usuario> getAllUsers() {
        List<Usuario> usuarios = userRepository.findAll();
        usuarios.forEach(usuario -> usuario.getPhones().size());
        return usuarios;

    }

    @Override
    public Optional<Usuario> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public Usuario saveUser(Usuario usuario) {
        if (userRepository.existsByEmail(usuario.getEmail())) {
            throw new DuplicateEmailException();
        }

        validarFormatoCorreo(usuario.getEmail());
        validarFormatoContrasena(usuario.getPassword());

        String token = jwtTokenProvider.generateToken(usuario.getEmail());

        usuario.setLastLogin(LocalDateTime.now());
        usuario.setToken(token);
        usuario.setActive(true);

        for (Phone phone : usuario.getPhones()) {
            phone.setUsuario(usuario);
        }

        Usuario savedUser = userRepository.save(usuario);
        return savedUser;
    }

    @Override
    public Optional<Usuario> findByNameAndPassword(String name, String password) {
        return userRepository.findByNameAndPassword(name, password);
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
    private void validarFormatoCorreo(String email) {
        String regexCorreo = "^[a-zA-Z0-9]+@dominio\\.cl$";
        if (!Pattern.matches(regexCorreo, email)) {
            throw new RuntimeException("El formato del correo no es válido");
        }
    }

    private void validarFormatoContrasena(String password) {
        if (!Pattern.matches(regexPassword, password)) {
            throw new RuntimeException("El formato de la contraseña no es válido");
        }
    }
}