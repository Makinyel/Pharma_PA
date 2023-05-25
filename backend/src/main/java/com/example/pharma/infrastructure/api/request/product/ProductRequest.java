package com.example.pharma.infrastructure.api.request.product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductRequest {

  private String name;
  private String description;
  private Double buyingPrice;
  private Double sellingPrice;
  private String brand;
  private String presentation;
  private String concentration;
}
