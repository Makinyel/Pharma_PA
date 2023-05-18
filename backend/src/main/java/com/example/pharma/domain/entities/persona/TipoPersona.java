package com.example.pharma.domain.entities.persona;

public enum TipoPersona {
    PROVIDER("PROVIDER"),
    CLIENT("CLIENT");

    String type;

    TipoPersona(String type) {
        this.type = type;
    }
}
