package com.example.pharma.infrastructure.api.request.compra;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PurchaseDetailRequest {

  private Integer quantity;
  private String destinationWarehouseName;
  private String productName;
  private Long purchaseId;
}
