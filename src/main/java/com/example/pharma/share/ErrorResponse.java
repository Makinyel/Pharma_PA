package com.example.pharma.share;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder

public class ErrorResponse {

    private int status;
    private String code;
    private String message;
    private String date;

    public ErrorResponse(int status, String code, String message, String date) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.date = date;
    }
}

