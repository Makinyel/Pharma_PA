package com.example.pharma.infrastructure.repository.compra;

import com.example.pharma.domain.entities.compra.CompraDetalle;
import feign.Param;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraDetalleRepository extends JpaRepository<CompraDetalle, Long> {

  @Query(nativeQuery = true, value = "select * from  where id_compra = :id_compra")
  List<CompraDetalle> findByIdCompra(@Param("id_compra") long idCompra);



  @Query(nativeQuery = true, value = "update compra_detalle set amount = :amount, " +
      "total =:total, id_BodegaDestino = :id_BodegaDestino, " + "id_producto = :id_producto "
      + "where id = :id")
  @Modifying
  public void editCompraDetalle(
      @Param("amount") int amount,
      @Param("id_BodegaDestino") Long id_BodegaDestino,
      @Param("total")  double total,
      @Param("id_producto") Long id_producto);
}

