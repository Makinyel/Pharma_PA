package com.example.pharma.domain.service;

import com.example.pharma.domain.entities.Keys.KeyStock;
import com.example.pharma.domain.entities.Stock;
import com.example.pharma.infrastructure.repository.StockRepository;
import com.example.pharma.infrastructure.repository.producto.StockDao;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class StockService {
    private StockRepository stockRepository;
    private StockDao stockDao;
  public Stock create(Stock stockDetalle) {

    return stockRepository.save(stockDetalle);

  }
  public List<Stock> getStock(Long idproducto){
    var stocks = stockDao.getStocks(idproducto);
    return stocks;
  }
  public Integer getTotalStocks(Long idproducto){
    var stocks = stockDao.getTotalStocksByidProducto(idproducto);
    return stocks;
  }

  public Integer getTotalStocks(Long idproducto, Long idBodega){
    var stocks = stockDao.getTotalStocksByidProductoIdBodega(idproducto,idBodega);
    return stocks;
  }

  public Stock updateStock(Stock stockDetalle) {
    var key = KeyStock.builder().id_producto(stockDetalle.getId_producto())
        .id_bodega(stockDetalle.getId_bodega()).build();
    var Stock = stockRepository.getById(key);
    if (Objects.isNull(Stock)){
      return stockRepository.save(stockDetalle);
    }
    return sumarStock(stockDetalle);
  }

  private Stock sumarStock(Stock stockDetalle){
    var key = KeyStock.builder().id_producto(stockDetalle.getId_producto())
        .id_bodega(stockDetalle.getId_bodega()).build();

    var stock = stockRepository.getById(key);

    stock.setStock(stockDetalle.getStock() + stock.getStock());
    return stockRepository.save(stock);
  }


}
