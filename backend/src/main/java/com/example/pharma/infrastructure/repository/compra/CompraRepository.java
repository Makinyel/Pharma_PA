package com.example.pharma.infrastructure.repository.compra;

import com.example.pharma.domain.entities.compra.Compra;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
  @Query(nativeQuery = true, value = "update compra set iva = :iva, " +
      "total =:total, total_with_iva = :totalWithIva where code = :code")
  @Modifying
  void editCompra(
      @Param("code") double code,
      @Param("total")  double total,
      @Param("totalWithIva")  double totalWithIva,
      @Param("iva") double iva);
}
