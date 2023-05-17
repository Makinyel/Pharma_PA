package com.example.pharma.infrastructure.repository;

import com.example.pharma.domain.entities.compra.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
}
