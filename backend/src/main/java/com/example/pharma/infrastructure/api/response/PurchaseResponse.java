package com.example.pharma.infrastructure.api.response;

import java.time.LocalDate;
import lombok.Data;

@Data
public class PurchaseResponse {

  private Long id;
  private LocalDate date;
  private String invoiceCode;
  private Double subtotal;
  private Double total;
  private Double iva;
  private String provider;
  private String user;
}
