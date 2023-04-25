package com.example.pharma.infrastructure.repository.producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.pharma.domain.entities.producto.Concentracion;

@Repository
public interface ConcentracionRepository extends JpaRepository<Concentracion, String> {
}
