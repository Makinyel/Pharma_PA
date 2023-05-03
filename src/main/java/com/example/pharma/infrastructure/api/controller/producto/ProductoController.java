package com.example.pharma.infrastructure.api.controller.producto;

import com.example.pharma.domain.entities.producto.Bodega;
import com.example.pharma.domain.entities.producto.Producto;
import com.example.pharma.domain.service.producto.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/producto")
public class ProductoController {

  private ProductoService productoService;

  @PostMapping
  public ResponseEntity<Producto> saveProducto(@RequestBody Producto producto) {

    return new ResponseEntity<>(productoService.saveProducto(producto), HttpStatus.CREATED);
  }

  @GetMapping()
  public ResponseEntity<List<Producto>> getAllProductos() {
    return ResponseEntity.ok(productoService.getAllProducts());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Producto> getProducto(@PathVariable String id) {
    return ResponseEntity.ok(productoService.getProducto(id));
  }
  @GetMapping("/findByBodega/{idBodega}")
  public ResponseEntity<List<Producto>> getAllProductosofBodega(@PathVariable String idBodega) {
    return ResponseEntity.ok(productoService.findAllByBodega(idBodega));
  }

}

