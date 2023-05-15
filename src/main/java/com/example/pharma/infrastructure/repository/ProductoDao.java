package com.example.pharma.infrastructure.repository;


import org.springframework.stereotype.Repository;

@Repository
public class ProductoDao {

  private final String findAllByIdBodega =
      "SELECT *"
          + "FROM stock st \n"
          + "INNER JOIN  maestro_producto mp ON st.id_producto = mp.id\n"
          + "where st.id_producto = :idProducto";
}
