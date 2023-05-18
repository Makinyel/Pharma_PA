package com.example.pharma.domain.entities.usuario;

public enum Role {
    ADMIN("ADMIN"),
    GERENTE_COMPRA("GERENTE_COMPRA"),
    GERENTE_VENTA("GERENTE_VENTA");

    String role;

    Role(String role) {
        this.role = role;
    }
}