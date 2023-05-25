package com.example.pharma.infrastructure.repository.sale;

import com.example.pharma.domain.entities.sale.SaleDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long> {

}
