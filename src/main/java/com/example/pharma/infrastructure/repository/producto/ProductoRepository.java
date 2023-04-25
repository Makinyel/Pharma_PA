package com.example.pharma.infrastructure.repository.producto;

import com.example.pharma.domain.entities.producto.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,String> {
}
