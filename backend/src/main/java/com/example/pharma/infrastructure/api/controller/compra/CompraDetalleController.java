package com.example.pharma.infrastructure.api.controller.compra;

import com.example.pharma.domain.entities.compra.CompraDetalle;
import com.example.pharma.domain.service.compra.CompraDetalleService;
import com.example.pharma.infrastructure.api.request.CompraDetalleRequest;
import com.example.pharma.infrastructure.mapper.CompraDetalleRequestMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
@RequestMapping(path = "/compradetalle")
public class CompraDetalleController {
  private CompraDetalleService compraDetalleService;
  private CompraDetalleRequestMapper compraDetalleRequestMapper;

  @PostMapping
  public void create(@RequestBody List<CompraDetalleRequest> compraDetalleRequest) {
    compraDetalleService.comprarProducto(compraDetalleRequestMapper.toEntity(compraDetalleRequest));
  }

}
