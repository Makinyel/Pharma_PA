package com.example.pharma.share;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequestMapping(MediaType.APPLICATION_JSON_VALUE)
public class BaseExeptionResolver {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> errorhander(BaseException ex) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .code(ex.getCode())
                .date(ex.getDate().toString())
                .status(ex.getStatus().value())
                .message(ex.getMessage())
                .build(),
                HttpStatusCode.valueOf(ex.getStatus().value()));
    }

}
