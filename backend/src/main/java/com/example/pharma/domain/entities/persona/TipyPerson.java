package com.example.pharma.domain.entities.persona;

public enum TipyPerson {
  PROVIDER("PROVIDER"),
  CLIENT("CLIENT");

  String type;

  TipyPerson(String type) {
    this.type = type;
  }
}
