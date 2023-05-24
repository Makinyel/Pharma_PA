package com.example.pharma.infrastructure.api.controller.compra;

import com.example.pharma.domain.entities.compra.Compra;
import com.example.pharma.domain.service.compra.CompraService;
import com.example.pharma.infrastructure.api.request.compra.CompraRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
@RequestMapping(path = "/compra")
public class CompraController {

  private CompraService compraService;

  @PostMapping
  public ResponseEntity<Compra> create(@RequestBody CompraRequest compraRequest) {
    return new ResponseEntity<Compra>(compraService.save(compraRequest),HttpStatus.CREATED);
  }

}