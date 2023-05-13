package com.example.pharma.domain.service;

import com.example.pharma.domain.entities.Stock;
import com.example.pharma.infrastructure.repository.StockRepository;
import com.example.pharma.infrastructure.repository.producto.StockDao;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class StockService {
    private StockRepository stockRepository;
    private StockDao stockDao;

  public Stock create(Stock stock) {
      return stockRepository.save(stock);
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

}
