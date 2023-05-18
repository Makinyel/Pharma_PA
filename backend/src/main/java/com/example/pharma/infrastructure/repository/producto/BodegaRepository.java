package com.example.pharma.infrastructure.repository.producto;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pharma.domain.entities.producto.Bodega;

@Repository
public interface BodegaRepository extends JpaRepository<Bodega, Long> {
  Optional<Bodega> findByNombre(String name);
}
