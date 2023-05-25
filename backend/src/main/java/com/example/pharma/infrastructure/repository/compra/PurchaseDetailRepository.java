package com.example.pharma.infrastructure.repository.compra;

import com.example.pharma.domain.entities.purchase.PurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail, Long> {

}

