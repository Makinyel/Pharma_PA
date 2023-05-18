package com.example.pharma.infrastructure.repository.producto;

import com.example.pharma.domain.entities.producto.Marca;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
  Optional<Marca> findByNombre(String name);
}
