package com.example.pharma.infrastructure.repository.producto;

import com.example.pharma.domain.entities.product.Brand;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

  Optional<Brand> findByName(String name);
}
