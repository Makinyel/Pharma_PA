package com.example.pharma.infrastructure.api.controller.producto;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pharma.domain.entities.producto.Presentacion;
import com.example.pharma.domain.service.producto.PresentacionService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/preparation")
public class PresentacionController {

  private PresentacionService presentacionService;

  @PostMapping
  public Presentacion save(@RequestBody Presentacion presentacion) {
    return presentacionService.save(presentacion);
  }

  @GetMapping
  public List<Presentacion> getAll() {
    return presentacionService.getAll();
  }

  @GetMapping("nombre")
  public Presentacion getByName(@RequestParam("name") String name) {
    return presentacionService.getByName(name);
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable("id") Long id) {
    presentacionService.delete(id);
  }
}
