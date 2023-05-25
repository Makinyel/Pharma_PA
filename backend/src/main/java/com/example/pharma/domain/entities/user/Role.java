package com.example.pharma.domain.entities.user;

public enum Role {
    ADMIN("ADMIN"),
    GERENTE_COMPRA("GERENTE_COMPRA"),
    GERENTE_VENTA("GERENTE_VENTA");

    String userRole;

    Role(String userRole) {
        this.userRole = userRole;
    }
}