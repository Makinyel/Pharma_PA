package com.example.pharma.domain.service;

import com.example.pharma.domain.entities.stock.Stock;
import com.example.pharma.domain.entities.Keys.KeyStock;
import com.example.pharma.infrastructure.repository.StockRepository;
import com.example.pharma.infrastructure.repository.producto.StockDao;
import com.example.pharma.shared.NotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class StockService {

  private final StockRepository stockRepository;
  private final StockDao stockDao;

  public Stock create(Stock stockDetalle) {
    var key = KeyStock.builder().id_producto(stockDetalle.getId_producto())
        .id_bodega(stockDetalle.getId_bodega()).build();

    Optional<Stock> stock = stockRepository.findById(key);

    if (stock.isPresent()) {
      return sumarStock(stockDetalle);
    } else {
      new NotFoundException("No such stock");
      return stockRepository.save(stockDetalle);
    }
  }

  public List<Stock> getStock(Long idproducto) {
    return stockDao.getStocks(idproducto);
  }

  public Integer getTotalStocks(Long idproducto) {
    var stocks = stockDao.getTotalStocksByidProducto(idproducto);
    return stocks;
  }

  public Integer getTotalStocks(Long idproducto, Long idBodega) {
    var stocks = stockDao.getTotalStocksByidProductoIdBodega(idproducto, idBodega);
    return stocks;
  }

  public Stock updateStock(Stock stockDetalle) {
    var key = KeyStock.builder().id_producto(stockDetalle.getId_producto())
        .id_bodega(stockDetalle.getId_bodega()).build();
    var stock = stockRepository.getById(key);
    if (Objects.isNull(stock)) {
      return stockRepository.save(stockDetalle);
    }else {
      return sumarStock(stockDetalle);
    }
  }
  public Stock sumarStockMovimientos(Stock stockDetalle,int cantidad) {

    System.out.println("Esta Sumando: " + stockDetalle.getCantidad());
    var key = KeyStock.builder().id_producto(stockDetalle.getId_producto())
        .id_bodega(stockDetalle.getId_bodega()).build();

    Optional<Stock> stockOptional = stockRepository.findById(key);

    Stock stock = stockOptional.get();
    stock.setCantidad(stockDetalle.getCantidad() + cantidad);
    return stockRepository.save(stock);
  }


  public Stock restarStock(Stock stockDetalle, int cantidad) {

    System.out.println("Esta Sumando: " + stockDetalle.getCantidad());
    var key = KeyStock.builder().id_producto(stockDetalle.getId_producto())
        .id_bodega(stockDetalle.getId_bodega()).build();

    Optional<Stock> stockOptional = stockRepository.findById(key);

    Stock stock = stockOptional.get();

    stock.setCantidad(stockDetalle.getCantidad() - cantidad);
    return stockRepository.save(stock);
  }
  private Stock sumarStock(Stock stockDetalle) {

    System.out.println("Esta Sumando: " + stockDetalle.getCantidad());
    var key = KeyStock.builder().id_producto(stockDetalle.getId_producto())
        .id_bodega(stockDetalle.getId_bodega()).build();

    Optional<Stock> stockOptional = stockRepository.findById(key);

    Stock stock = stockOptional.get();
    stock.setCantidad(stockDetalle.getCantidad() + stock.getCantidad());
    return stockRepository.save(stock);
  }
}

