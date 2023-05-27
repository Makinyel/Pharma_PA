package com.example.pharma.infrastructure.api.request.sale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleDetailRequest {

  private Integer quantity;
  private String sourceWarehouseName;
  private String productName;
  private Long saleId;
}
