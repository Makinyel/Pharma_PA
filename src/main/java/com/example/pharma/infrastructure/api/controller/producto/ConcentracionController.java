package com.example.pharma.infrastructure.api.controller.producto;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pharma.domain.entities.producto.Concentracion;
import com.example.pharma.domain.service.producto.ConcentracionService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/concentracion")
public class ConcentracionController {
    private ConcentracionService concentracionService;

    @PostMapping
    public Concentracion guardar(@RequestBody Concentracion concentracion) {
        concentracionService.guardar(concentracion);
        return concentracion;
    }

    @GetMapping
    public List<Concentracion> getAll() {
        return concentracionService.getAll();
    }

    @GetMapping("/nombre")
    public Concentracion getConcentracionByName(@RequestParam("name") String name) {
        return concentracionService.getConcentracionByName(name);
    }
}
