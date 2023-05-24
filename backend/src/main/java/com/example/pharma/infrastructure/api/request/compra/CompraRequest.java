package com.example.pharma.infrastructure.api.request.compra;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompraRequest {

  private String invoiceCode;
  private String providerName;
}
