package com.example.pharma.infrastructure.api.controller.compra;

import com.example.pharma.domain.entities.compra.Compra;
import com.example.pharma.domain.service.compra.CompraService;
import com.example.pharma.infrastructure.api.request.compra.CompraRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/purchase")
public class CompraController {

  private CompraService compraService;

  @PostMapping
  public ResponseEntity<Compra> create(
      @RequestBody CompraRequest compraRequest,
      @RequestHeader("user-email") String email) {
    return ResponseEntity.ok(compraService.save(compraRequest, email));
  }
}
