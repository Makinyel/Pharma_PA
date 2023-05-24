package com.example.pharma.domain.service.stock;

import com.example.pharma.domain.entities.keys.KeyStock;
import com.example.pharma.domain.entities.stock.Stock;
import com.example.pharma.infrastructure.repository.stock.StockDao;
import com.example.pharma.infrastructure.repository.stock.StockRepository;
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

  public Stock create(Stock stockDetalle, int cantidad) {

    KeyStock key = KeyStock.builder().id_producto(stockDetalle.getId_producto())
        .id_bodega(stockDetalle.getId_bodega()).build();

    Optional<Stock> stockOptional = stockRepository.findById(key);

    if (stockOptional.isPresent()) {
      Stock stock = stockOptional.get();
      return sumarStockMovimientos(stock, cantidad);
    } else {
      stockDetalle.setCantidad(cantidad);
      return stockRepository.save(stockDetalle);
    }
  }

  public Stock getById(KeyStock key) {
    return stockRepository.findById(key).
        orElseThrow(() -> new NotFoundException("No such stock"));
  }

  public List<Stock> getStock(Long idproducto) {
    return stockDao.getStocks(idproducto);
  }

  public Stock getByIdProductIdBodega(Long idproduct, Long idBodega) {

    KeyStock key = KeyStock.builder().id_bodega(idBodega).id_producto(idproduct).build();

    if (Objects.isNull(key)) {
      new NotFoundException("KeyStock not found");
    }

    Stock stock = getById(key);

    return stock;
  }

  public Integer getTotalStocks(Long idproducto) {
    Integer stocks = stockDao.getTotalStocksByidProducto(idproducto);
    return stocks;
  }

  public Integer getTotalStocks(Long idproducto, Long idBodega) {
    Integer stocks = stockDao.getTotalStocksByidProductoIdBodega(idproducto, idBodega);
    return stocks;
  }

  public Stock sumarStockMovimientos(Stock stockDetalle, int cantidad) {

    System.out.println("Esta Sumando: " + stockDetalle.getCantidad());

    stockDetalle.setCantidad(stockDetalle.getCantidad() + cantidad);

    return stockRepository.save(stockDetalle);
  }

  public Stock restarStockMovimientos(Stock stockDetalle, int cantidad) {

    System.out.println("Esta Restando: " + stockDetalle.getCantidad());

    stockDetalle.setCantidad(stockDetalle.getCantidad() - cantidad);

    return stockRepository.save(stockDetalle);
  }
}
