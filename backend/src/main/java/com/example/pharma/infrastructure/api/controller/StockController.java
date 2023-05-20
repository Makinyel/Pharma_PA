package com.example.pharma.infrastructure.api.controller;

import com.example.pharma.domain.entities.stock.Stock;
import com.example.pharma.domain.service.StockService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/stock")
public class StockController {

  private final StockService stockService;

  @PostMapping
  public ResponseEntity<Stock> save(@RequestBody Stock stock) {
    return  new ResponseEntity<>(stockService.create(stock),  HttpStatus.CREATED);
  }

  @GetMapping("{stockId}")
  public  ResponseEntity<List<Stock>> getAllStockByidProducto(@PathVariable("stockId") Long id) {
    return ResponseEntity.ok(stockService.getStock(id));
  }
  @GetMapping("{idProduct}/{warehouseId}")
  public  ResponseEntity <Stock> getByIdProductIdBodega(@PathVariable("idProduct") Long idProduct,
      @PathVariable("warehouseId") Long warehouseId) {
    return ResponseEntity.ok(stockService.getByIdProductIdBodega(idProduct,warehouseId));
  }

  @GetMapping("/total/{productId}")
  public ResponseEntity<Integer> getTotalStocksByidProducto(@PathVariable("productId") Long id) {
    return ResponseEntity.ok(stockService.getTotalStocks(id));
  }

  @GetMapping("/total/{productId}/{warehouseId}")
  public ResponseEntity<Integer> getTotalStocksByidProductoAndidBodega(
          @PathVariable("productId") Long productId,
          @PathVariable("warehouseId") Long warehouseId) {
    return ResponseEntity.ok(stockService.getTotalStocks(productId, warehouseId));
  }
}
