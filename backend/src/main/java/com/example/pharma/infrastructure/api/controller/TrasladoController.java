package com.example.pharma.infrastructure.api.controller;

import com.example.pharma.domain.entities.traslado.Traslado;
import com.example.pharma.domain.service.TrasladoService;
import com.example.pharma.infrastructure.api.request.TrasladoRequest;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/traslado")
public class TrasladoController {

  private final TrasladoService trasladoService;

  @PostMapping
  public ResponseEntity<Traslado> save(@RequestBody TrasladoRequest trasladoRequest) {
    return new ResponseEntity<>(trasladoService.create(trasladoRequest), HttpStatus.CREATED);
  }

  @GetMapping()
  public ResponseEntity<List<Traslado>> getAll() {
    return ResponseEntity.ok(trasladoService.getAll());
  }
}
