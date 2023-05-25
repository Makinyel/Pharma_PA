package com.example.pharma.infrastructure.repository.producto;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pharma.domain.entities.product.Preparation;

@Repository
public interface PreparationRepository extends JpaRepository<Preparation, Long> {
  Optional<Preparation> findByName(String name);
}
