package com.example.pharma.infrastructure.api.request.compra;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompraRequest {
  private String codeFactura;
  private String proveedor;
  private String usuario;
}
