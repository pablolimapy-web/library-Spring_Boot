package com.library.domain.service;

import com.library.domain.model.User;
import com.library.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Injeção de dependência via construtor
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Criar ou atualizar um usuário
    public User saveUser(User user) throws IllegalArgumentException {
        // Verificar se já existe um usuário com o mesmo email
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent() && !existingUser.get().getId().equals(user.getId())) {
            throw new IllegalArgumentException("Email already in use");
        }
        return userRepository.save(user);
    }

    // Buscar todos os usuários
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Buscar usuário por ID
    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    // Buscar usuário por email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Deletar usuário
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
