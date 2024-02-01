package com.exercise.usuario.controller;

import com.exercise.usuario.dto.UserRequest;
import com.exercise.usuario.dto.UserResponse;
import com.exercise.usuario.mapper.UserMapper;
import com.exercise.usuario.model.Usuario;
import com.exercise.usuario.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@Api(tags = "users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public List<Usuario> getAlUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable UUID id) {
        Optional<Usuario> cuenta = userService.getUserById(id);
        return cuenta.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        Usuario usuario = userMapper.convertToUser(userRequest);
        UserResponse userResponse = userMapper.convertToUserResponse(userService.saveUser(usuario));
        return ResponseEntity.ok(userResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Usuario> updatUser(
            @PathVariable UUID id,
            @RequestBody  UserRequest userRequest
    ) {
        return handleUpdate(id, userRequest);
    }

   private ResponseEntity<Usuario> handleUpdate(UUID id, UserRequest userRequest) {

        Optional<Usuario> existingUserOptional = userService.getUserById(id);

        if (existingUserOptional.isPresent()) {
            Usuario existingUser= existingUserOptional.get();
            userMapper.updateUserFromRequest(userRequest, existingUser);
            existingUser.setLastLogin(LocalDateTime.now());
            Usuario updatedUser = userService.saveUser(existingUser);
            return ResponseEntity.ok(updatedUser);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable UUID id) {
        Optional<Usuario> user = userService.getUserById(id);

        if (user.isPresent()) {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}