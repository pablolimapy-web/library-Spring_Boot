package com.library.domain.repository;

import com.library.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    // Buscar usuário por email
    Optional<User> findByEmail(String email);
}
