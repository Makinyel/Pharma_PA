package com.example.pharma.infrastructure.repository;

import com.example.pharma.domain.entities.producto.Bodega;
import com.example.pharma.domain.entities.usuario.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
