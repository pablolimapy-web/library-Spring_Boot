package com.library.controller;

import com.library.domain.model.User;
import com.library.domain.service.UserService;
import com.library.application.dto.UserDTO;
import com.library.application.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // Injeção de dependência via construtor
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET /users → retorna todos os usuários
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    // GET /users/{id} → retorna um usuário específico
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {
        return userService.getUserById(id)
                .map(UserMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /users → cria um novo usuário
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        try {
            User user = UserMapper.toEntity(userDTO);
            User savedUser = userService.saveUser(user);
            return ResponseEntity.ok(UserMapper.toDTO(savedUser));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // PUT /users/{id} → atualiza um usuário
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable UUID id, @RequestBody UserDTO userDTO) {
        return userService.getUserById(id)
                .map(existing -> {
                    existing.setName(userDTO.getName());
                    existing.setEmail(userDTO.getEmail());
                    try {
                        User updated = userService.saveUser(existing);
                        return ResponseEntity.ok(UserMapper.toDTO(updated));
                    } catch (IllegalArgumentException e) {
                        return ResponseEntity.badRequest().body(e.getMessage());
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /users/{id} → deleta um usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
