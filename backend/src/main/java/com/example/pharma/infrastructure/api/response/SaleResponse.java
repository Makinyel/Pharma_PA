package com.example.pharma.infrastructure.api.response;

import java.time.LocalDate;
import lombok.Data;

@Data
public class SaleResponse {

  private Long id;
  private LocalDate date;
  private Double subtotal;
  private Double total;
  private Double iva;
  private String client;
  private String user;
}
