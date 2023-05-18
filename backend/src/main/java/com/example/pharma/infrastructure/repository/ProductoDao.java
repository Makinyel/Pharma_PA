package com.example.pharma.infrastructure.repository;


import com.example.pharma.domain.entities.producto.Producto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class ProductoDao {

  private final String findAllByIdBodega =
      "SELECT maestro_producto.id, maestro_producto.nombre, maestro_producto.descripcion,maestro_producto.precio_costo, maestro_producto.precio_venta, maestro_producto.id_concentracion, maestro_producto.id_marca, maestro_producto.id_presentacion \n"
          + "FROM maestro_producto\n"
          + "INNER JOIN  stock st ON st.id_producto = maestro_producto.id \n"
          + "where st.id_bodega = :bodegaId";

  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  public List<Producto> findAllByIdBodega(Long bodegaId) {
    SqlParameterSource parameters = new MapSqlParameterSource()
        .addValue("bodegaId", bodegaId);

    List<Producto> listProductos = namedParameterJdbcTemplate.query(
        findAllByIdBodega, parameters,
        new BeanPropertyRowMapper<>(Producto.class));

    return listProductos;

  }
}
