package com.example.pharma.infrastructure.repository.producto;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pharma.domain.entities.product.Concentration;

@Repository
public interface ConcentrationRepository extends JpaRepository<Concentration, Long> {
  Optional<Concentration> findByName(String name);
}
