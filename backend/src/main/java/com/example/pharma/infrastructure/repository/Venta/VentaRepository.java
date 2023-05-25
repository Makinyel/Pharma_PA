package com.example.pharma.infrastructure.repository.Venta;

import com.example.pharma.domain.entities.venta.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends JpaRepository<Venta,Long> {
}
