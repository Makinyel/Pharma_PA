package com.example.pharma.infrastructure.api.controller.compra;

import com.example.pharma.domain.entities.purchase.Purchase;
import com.example.pharma.domain.service.purchase.PurchaseService;
import com.example.pharma.infrastructure.api.request.compra.PurchaseRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/buy")
public class CompraController {

  private PurchaseService purchaseService;

  @PostMapping
  public ResponseEntity<Purchase> create(
      @RequestBody PurchaseRequest purchaseRequest,
      @RequestHeader("user-email") String email) {
    return ResponseEntity.ok(purchaseService.initPurchase(purchaseRequest, email));
  }
}
