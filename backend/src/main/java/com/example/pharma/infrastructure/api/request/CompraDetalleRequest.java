package com.example.pharma.infrastructure.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class CompraDetalleRequest {
  private Integer amount;
  private String bodegaDestino;
  private String producto;
  private Long id_compra;
}
