package com.example.pharma.infrastructure.repository;

import com.example.pharma.domain.entities.Usuario.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol,String> {
}
