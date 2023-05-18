package com.example.pharma.infrastructure.repository.producto;


import com.example.pharma.domain.entities.stock.Stock;
import com.example.pharma.infrastructure.repository.StockRepository;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class StockDao {

  private final String GET_ALL_STOCKS =
      "SELECT *"
      + "FROM stock st \n"
      + "INNER JOIN  maestro_producto mp ON st.id_producto = mp.id\n"
      + "where st.id_producto = :idProducto";

  private final String GET_VALUE_STOCKS_BY_IDPRODUCT_AND_IDBODEGA =
      "SELECT *\n"
      + "FROM stock st \n"
      + "INNER JOIN  maestro_producto mp ON st.id_producto = mp.id\n"
      + "INNER JOIN  maestro_bodega mb ON st.id_bodega = mb.id\n"
      + "where st.id_producto = :idProducto and st.id_bodega = :idBodega";

  private final String UPDATE_VALUE_STOCKS_BY_IDPRODUCT_AND_IDBODEGA =
      "UPDATE st set st.cantidad\n"
          + "FROM stock st \n"
          + "INNER JOIN  maestro_producto mp ON st.id_producto = mp.id\n"
          + "INNER JOIN  maestro_bodega mb ON st.id_bodega = mb.id\n"
          + "where st.id_producto = :idProducto and st.id_bodega = :idBodega";
  private String GET_ALL_STOCKS_BY_IDPRODUCT_AND_IDBODEGA;
  private StockRepository stockRepository;

  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  public List<Stock> getStocks(Long idProducto) {
    SqlParameterSource parameters = new MapSqlParameterSource()
        .addValue("idProducto", idProducto);

    List<Stock> listaStock = namedParameterJdbcTemplate.query(GET_ALL_STOCKS, parameters, new
        BeanPropertyRowMapper<>(Stock.class));

    var totalstock = 0;
    listaStock.forEach(stock -> {
      var total = stock.getCantidad() + totalstock;
    });
    return listaStock;
  }

  public Integer getTotalStocksByidProducto(Long idProducto) {
    SqlParameterSource parameters = new MapSqlParameterSource()
        .addValue("idProducto", idProducto);

    List<Stock> listaStock = namedParameterJdbcTemplate.query(GET_ALL_STOCKS, parameters, new
        BeanPropertyRowMapper<>(Stock.class));
    AtomicInteger totalStock = new AtomicInteger();
    listaStock.forEach(stock -> {
      totalStock.addAndGet(stock.getCantidad());
    });
    return totalStock.get();
  }

  public Integer getTotalStocksByidProductoIdBodega(Long idProducto, Long idBodega) {
    MapSqlParameterSource parameterSource = new MapSqlParameterSource();

    parameterSource.addValue("idProducto", idProducto);
    parameterSource.addValue("idBodega", idBodega);

    List<Stock> listaStock = namedParameterJdbcTemplate.query(
        GET_VALUE_STOCKS_BY_IDPRODUCT_AND_IDBODEGA, parameterSource, new
            BeanPropertyRowMapper<>(Stock.class));
    AtomicInteger totalStock = new AtomicInteger();
    listaStock.forEach(stock -> {
      totalStock.addAndGet(stock.getCantidad());
    });
    return totalStock.get();
  }


}
