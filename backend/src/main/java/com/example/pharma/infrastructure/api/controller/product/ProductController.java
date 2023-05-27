package com.example.pharma.infrastructure.api.controller.product;

import com.example.pharma.domain.entities.product.Product;
import com.example.pharma.domain.service.product.ProductService;
import com.example.pharma.infrastructure.api.mapper.ProductResponseMapper;
import com.example.pharma.infrastructure.api.request.product.ProductRequest;
import com.example.pharma.infrastructure.api.response.ProductResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

  private ProductService productService;
  private ProductResponseMapper responseMapper;

  @PostMapping
  public ResponseEntity<Product> save(@RequestBody ProductRequest productRequest) {
    return ResponseEntity.ok(productService.save(productRequest));
  }

  @GetMapping
  public ResponseEntity<List<ProductResponse>> findAll() {
    return ResponseEntity.ok(responseMapper.toResponse(productService.findAll()));
  }

  @GetMapping("/detail")
  public ResponseEntity<ProductResponse> findByName(@RequestParam("name") String name) {
    return ResponseEntity.ok(responseMapper.toResponse(productService.findByName(name)));
  }

  @GetMapping("{id}")
  public ResponseEntity<Product> findByName(@PathVariable("id") Long id) {
    return ResponseEntity.ok(productService.findById(id));
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable("id") Long id) {
    productService.delete(id);
  }
}
