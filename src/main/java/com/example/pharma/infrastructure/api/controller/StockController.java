package com.example.pharma.infrastructure.api.controller;

import com.example.pharma.domain.entities.Stock;
import com.example.pharma.domain.service.StockService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/stock")
public class StockController {

  private StockService productoBodegaService;

  @PostMapping
  public Stock save(@RequestBody Stock stock) {
    return productoBodegaService.create(stock);
  }

  @GetMapping("{stockId}")
  public List<Stock> getStockByidProducto(@PathVariable("stockId") Long id) {
    return productoBodegaService.getStock(id);
  }

  @GetMapping("/total/{productId}")
  public Integer getTotalStocksByidProducto(@PathVariable("productId") Long id) {
    return productoBodegaService.getTotalStocks(id);
  }

  @GetMapping("/total/{productId}/{warehouseId}")
  public Integer getTotalStocksByidProductoAndidBodega(
          @PathVariable("productId") Long productId,
          @PathVariable("warehouseId") Long warehouseId) {
    return productoBodegaService.getTotalStocks(productId, warehouseId);
  }
}
