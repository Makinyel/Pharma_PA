package com.example.pharma.infrastructure.repository.producto;

import com.example.pharma.domain.entities.product.Warehouse;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

  Optional<Warehouse> findByName(String name);
}
