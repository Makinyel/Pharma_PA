package com.example.pharma.infrastructure.repository.producto;

import com.example.pharma.domain.entities.producto.Presentacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentacionRepository extends JpaRepository<Presentacion, String> {
}
