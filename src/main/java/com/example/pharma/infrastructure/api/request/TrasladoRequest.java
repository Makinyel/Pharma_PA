package com.example.pharma.infrastructure.api.request;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrasladoRequest {

  private int cantidad;
  private String producto;
  private String bodegaOrigen;
  private String bodegaDestino;
  private String user;

}
