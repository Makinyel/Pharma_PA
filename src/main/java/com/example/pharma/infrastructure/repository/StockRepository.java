package com.example.pharma.infrastructure.repository;

import com.example.pharma.domain.entities.Stock;
import com.example.pharma.domain.entities.producto.Bodega;
import com.example.pharma.domain.entities.producto.Producto;
import feign.Param;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

}
