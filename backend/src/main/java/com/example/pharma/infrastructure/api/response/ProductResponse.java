package com.example.pharma.infrastructure.api.response;

import lombok.Data;

@Data
public class ProductResponse {

  private Long id;
  private String name;
  private String description;
  private Double buyingPrice;
  private Double sellingPrice;
  private String brand;
  private String preparation;
  private String concentration;
  private String prefix;
}
