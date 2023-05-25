package com.example.pharma.infrastructure.repository.sale;

import com.example.pharma.domain.entities.sale.Sale;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

  @Query(nativeQuery = true, value = "update venta set iva = :iva, " +
      "total =:total, subtotal = :subtotal where id = :id")
  @Modifying
  void updateSale(
      @Param("id") double id,
      @Param("subtotal") double subtotal,
      @Param("total") double total,
      @Param("iva") double iva);

}
