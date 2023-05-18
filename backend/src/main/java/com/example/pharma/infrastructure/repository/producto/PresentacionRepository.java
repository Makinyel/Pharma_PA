package com.example.pharma.infrastructure.repository.producto;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pharma.domain.entities.producto.Presentacion;

@Repository
public interface PresentacionRepository extends JpaRepository<Presentacion, Long> {
  Optional<Presentacion> findByNombre(String name);
}
