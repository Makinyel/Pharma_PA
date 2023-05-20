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
      "total =:total, totalWithIva = :totalWithIva, " +
      "where code = :code")
  @Modifying
  public void editCompra(
      @Param("total")  double total,
      @Param("totalWithIva")  double totalWithIva,
      @Param("iva") double iva);
}
