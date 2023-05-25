package com.example.pharma.infrastructure.api.request.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransferDetailRequest {

  private int quantity;
  private String productName;
  private String origenwarehouseName;
  private String destinationWarehouseName;
}
