package com.example.pharma.shared;

public enum ExceptionCode {

  NOT_FOUND("111", "NOT_FOUND");
  private final String code;
  private final String type;


  ExceptionCode(String code, String type) {
    this.code = code;
    this.type = type;
  }

  public String getCode() {
    return code;
  }

  public String getType() {
    return type;
  }


}
