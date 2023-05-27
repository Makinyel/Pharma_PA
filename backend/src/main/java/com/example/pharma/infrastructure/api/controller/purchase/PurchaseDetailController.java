package com.example.pharma.infrastructure.api.controller.purchase;

import com.example.pharma.domain.service.purchase.PurchaseDetailService;
import com.example.pharma.infrastructure.api.mapper.PurchaseResponseMapper;
import com.example.pharma.infrastructure.api.request.compra.PurchaseDetailRequest;
import com.example.pharma.infrastructure.api.response.PurchaseResponse;
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

  private final PurchaseDetailService purchaseDetailService;
  private final PurchaseResponseMapper responseMapper;

  @PostMapping
  public PurchaseResponse create(@RequestBody List<PurchaseDetailRequest> purchaseDetailRequest) {
    return responseMapper.toResponse(purchaseDetailService.completePurchase(
        purchaseDetailService.mapper(purchaseDetailRequest)));
  }
}
