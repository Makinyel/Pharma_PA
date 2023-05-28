package com.example.pharma.infrastructure.api.controller.purchase;

import com.example.pharma.domain.entities.purchase.Purchase;
import com.example.pharma.domain.service.purchase.PurchaseService;
import com.example.pharma.infrastructure.api.request.compra.PurchaseRequest;
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
@RequestMapping(path = "/buy")
public class PurchaseController {

  private PurchaseService purchaseService;

  @PostMapping
  public ResponseEntity<Purchase> create(
      @RequestBody PurchaseRequest purchaseRequest,
      @RequestHeader("user-email") String email) {
    return ResponseEntity.ok(purchaseService.initPurchase(purchaseRequest, email));
  }

  @GetMapping
  public ResponseEntity<List<Purchase>> findAll() {
    return ResponseEntity.ok(purchaseService.findAll());
  }

  @GetMapping("/last")
  public ResponseEntity<Purchase> findLast() {
    return ResponseEntity.ok(purchaseService.findLast());
  }
}
