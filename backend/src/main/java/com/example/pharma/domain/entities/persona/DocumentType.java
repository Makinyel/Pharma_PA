package com.example.pharma.domain.entities.persona;

public enum DocumentType {

  TI("TI"),
  CEDULA("CEDULA"),
  PASAPORTE("PASAPORTE");

  final String type;

  DocumentType(String type) {
    this.type = type;
  }
}