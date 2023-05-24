package com.example.pharma.infrastructure.api.controller.producto;

import com.example.pharma.domain.entities.producto.Producto;
import com.example.pharma.domain.service.producto.ProductoService;
import com.example.pharma.infrastructure.api.request.product.ProductRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductoController {

  private ProductoService productoService;

  @PostMapping
  public ResponseEntity<Producto> save(@RequestBody ProductRequest productRequest) {
    return new ResponseEntity<>(productoService.save(productRequest), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<Producto>> getAll() {
    return ResponseEntity.ok(productoService.getAll());
  }

  @GetMapping("/detail")
  public ResponseEntity<Producto> getById(@RequestParam Long id) {
    return ResponseEntity.ok(productoService.getById(id));
  }

  @GetMapping("/findByBodega/{idBodega}")
  public ResponseEntity<List<Producto>> getAllByWarehouse(@PathVariable Long idBodega) {
    return ResponseEntity.ok(productoService.findAllByIdBodega(idBodega));
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable("id") Long id) {
    productoService.delete(id);
  }
}
