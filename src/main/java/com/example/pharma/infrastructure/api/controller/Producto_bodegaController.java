package com.example.pharma.infrastructure.api.controller;

import com.example.pharma.domain.entities.Stock;
import com.example.pharma.domain.service.StockService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/stock")
public class Producto_bodegaController {

  private StockService productoBodegaService;

  @PostMapping
  public Stock create(@RequestBody Stock producto_bodega){
    return productoBodegaService.create(producto_bodega);
  }
  @GetMapping("/{idProducto}")
  public List<Stock> getStockByidProducto(@PathVariable("idProducto") String idproducto) {
    System.out.println("ESTA ENTRADO: " + idproducto);
    return productoBodegaService.getStock(Long.parseLong(idproducto));
  }

  @GetMapping("/getTotal/{idProducto}")
  public Integer getTotalStocksByidProducto(@PathVariable("idProducto") String idproducto) {
    return productoBodegaService.getTotalStocks(Long.parseLong(idproducto));
  }
  @GetMapping("/getTotal/{idProducto}/{idBodega}")
  public Integer getTotalStocksByidProductoAndidBodega(@PathVariable("idProducto") String idproducto,
  @PathVariable("idBodega") String idBodega) {
    return productoBodegaService.getTotalStocks(Long.parseLong(idproducto),Long.parseLong(idBodega));
  }
}
