package com.example.pharma.infrastructure.api.controller.sale;

import com.example.pharma.domain.entities.sale.Sale;
import com.example.pharma.domain.service.sale.SaleService;
import com.example.pharma.infrastructure.api.request.sale.SaleRequest;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/sale")
public class SaleController {

  private SaleService saleService;

  @PostMapping
  public ResponseEntity<Sale> create(
      @RequestBody SaleRequest saleRequest,
      @RequestHeader("user-email") String email) {
    return ResponseEntity.ok(saleService.initSale(saleRequest, email));
  }

  @GetMapping
  public ResponseEntity<List<Sale>> findAll() {
    return ResponseEntity.ok(saleService.findAll());
  }

  @GetMapping("/last")
  public ResponseEntity<Sale> findLast() {
    return ResponseEntity.ok(saleService.findLast());
  }
}
