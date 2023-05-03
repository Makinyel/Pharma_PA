package com.example.pharma.infrastructure.repository.producto;

import com.example.pharma.domain.entities.producto.Bodega;
import com.example.pharma.domain.entities.producto.Producto;
import feign.Param;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,String> {
  @Query(value = "SELECT * FROM maestro_producto WHERE id_bodega = :idBodega", nativeQuery = true)
  List<Producto> findAllByIdBodega(@Param("idBodega") String idBodega);

  @Query(value = "SELECT * FROM maestro_bodega WHERE id = :id", nativeQuery = true)
  Bodega findBodegaByIdBodega(@Param String id);

}
