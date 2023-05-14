package com.example.pharma.infrastructure.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductRequest {
    private String nombre;
    private String descripcion;
    private double precioCosto;
    private double precioVenta;
    private String marca;
    private String presentacion;
    private String concentracion;
}
