package com.example.pharma.infrastructure.repository.usuario;

import com.example.pharma.domain.entities.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);
}
