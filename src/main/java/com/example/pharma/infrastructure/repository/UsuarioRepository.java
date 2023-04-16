package com.example.pharma.infrastructure.repository;

import com.example.pharma.domain.entities.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UsuarioRepository extends JpaRepository<Usuario,String> {
}
