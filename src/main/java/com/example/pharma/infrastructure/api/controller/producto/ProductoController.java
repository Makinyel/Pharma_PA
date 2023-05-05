package com.example.pharma.infrastructure.api.controller.producto;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pharma.domain.entities.producto.Producto;
import com.example.pharma.domain.service.producto.ProductoService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/producto")
public class ProductoController {

  private ProductoService productoService;

  @PostMapping
  public ResponseEntity<Producto> saveProducto(@RequestBody Producto producto) {
    return new ResponseEntity<>(productoService.saveProducto(producto), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Producto>> getAllProductos() {
    return ResponseEntity.ok(productoService.getAllProducts());
  }

  @GetMapping("/detail")
  public ResponseEntity<Producto> getProducto(@RequestParam Long id) {
    return ResponseEntity.ok(productoService.getProducto(id));
  }

  @GetMapping("/findByBodega/{idBodega}")
  public ResponseEntity<List<Producto>> getAllProductosofBodega(@PathVariable Long idBodega) {
    return ResponseEntity.ok(productoService.findAllByBodega(idBodega));
  }
}
