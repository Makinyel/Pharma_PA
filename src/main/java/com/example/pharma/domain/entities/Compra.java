package com.example.pharma.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Compra extends Movimiento {
    private String code;
    private String idbodegaDestino;
    private String idProveedor;
}