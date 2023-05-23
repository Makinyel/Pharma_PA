package com.example.pharma.infrastructure.api.controller.compra;

import com.example.pharma.domain.service.compra.CompraDetalleService;
import com.example.pharma.infrastructure.api.request.compra.CompraDetalleRequest;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
@RequestMapping(path = "/compradetalle")
public class CompraDetalleController {
  private CompraDetalleService compraDetalleService;


  @PostMapping
  public void create(@RequestBody List<CompraDetalleRequest> compraDetalleRequest) {
    compraDetalleService.comprarProducto(compraDetalleService.mapper(compraDetalleRequest));
  }
}
