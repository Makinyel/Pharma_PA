package com.example.pharma.domain.entities.usuario;

public enum Rol {
    admin("ADMIN"),
    gerenteCompra("GERCOM"),
    gerenteVenta("GERVEN"),
    analista("ANALIST");

    String rol;

    Rol(String rol) {
        this.rol = rol;
    }
}