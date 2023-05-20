package com.example.pharma.infrastructure.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompraRequest {
  private String codeFactura;
  private double iva;
  private String proveedor;
  private String usuario;
}
