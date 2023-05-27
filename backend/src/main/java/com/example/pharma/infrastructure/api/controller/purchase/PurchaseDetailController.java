package com.example.pharma.infrastructure.api.controller.purchase;

import com.example.pharma.domain.service.purchase.PurchaseDetailService;
import com.example.pharma.infrastructure.api.request.compra.PurchaseDetailRequest;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/buy-details")
public class PurchaseDetailController {

  private PurchaseDetailService purchaseDetailService;

  @PostMapping
  public void create(@RequestBody List<PurchaseDetailRequest> purchaseDetailRequest) {
    purchaseDetailService.completePurchase(purchaseDetailService.mapper(purchaseDetailRequest));
  }
}
