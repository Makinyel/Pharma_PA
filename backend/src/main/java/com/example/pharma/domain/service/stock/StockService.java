package com.example.pharma.domain.service.stock;

import com.example.pharma.domain.entities.keys.KeyStock;
import com.example.pharma.domain.entities.stock.Stock;
import com.example.pharma.infrastructure.repository.stock.StockRepository;
import com.example.pharma.shared.NotFoundException;
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

  public Stock create(Stock stockDetalle, int cantidad) {

    KeyStock key = KeyStock.builder().productId(stockDetalle.getProductId())
        .warehouseId(stockDetalle.getWarehouseId()).build();

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


  public Stock getByIdProductIdBodega(Long idproduct, Long idBodega) {

    KeyStock key = KeyStock.builder().warehouseId(idBodega).productId(idproduct).build();

    if (Objects.isNull(key)) {
      throw new NotFoundException("KeyStock not found");
    }

    return getById(key);
  }


  public Stock sumarStockMovimientos(Stock stockDetalle, int cantidad) {

    stockDetalle.setCantidad(stockDetalle.getCantidad() + cantidad);

    return stockRepository.save(stockDetalle);
  }

  public Stock restarStockMovimientos(Stock stockDetalle, int cantidad) {

    stockDetalle.setCantidad(stockDetalle.getCantidad()
        - cantidad);

    return stockRepository.save(stockDetalle);
  }
}
