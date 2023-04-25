package com.example.pharma.infrastructure.api.controller.producto;

import com.example.pharma.domain.entities.producto.Presentacion;
import com.example.pharma.domain.service.producto.PresentacionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/presentacion")
public class PresentacionController {

  private PresentacionService presentacionService;

  @PostMapping
  public Presentacion guardar(@RequestBody Presentacion presentacion) {
    presentacionService.guardar(presentacion);
    return presentacion;
  }
}
