package com.example.pharma.infrastructure.repository.compra;

import com.example.pharma.domain.entities.purchase.Purchase;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
  @Query(nativeQuery = true, value = "update compra set iva = :iva, " +
      "total =:total, subtotal = :subtotal where id = :id")
  @Modifying
  void updatePurchase(
      @Param("id") double id,
      @Param("total")  double total,
      @Param("subtotal")  double subtotal,
      @Param("iva") double iva);
}
