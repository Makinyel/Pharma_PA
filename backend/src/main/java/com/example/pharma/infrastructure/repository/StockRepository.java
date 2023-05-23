package com.example.pharma.infrastructure.repository;

import com.example.pharma.domain.entities.keys.KeyStock;
import com.example.pharma.domain.entities.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, KeyStock> {

}
