package com.example.pharma.infrastructure.api.request.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransferDetailRequest {

  private Integer quantity;
  private String productName;
  private String sourceWarehouse;
  private String destinationWarehouse;
}
