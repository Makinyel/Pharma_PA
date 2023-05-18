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
@RequestMapping(path = "/concentration")
public class ConcentracionController {
    private ConcentracionService concentracionService;

    @PostMapping
    public Concentracion save(@RequestBody Concentracion concentracion) {
        concentracionService.save(concentracion);
        return concentracion;
    }

    @GetMapping
    public List<Concentracion> getAll() {
        return concentracionService.getAll();
    }

    @GetMapping("/nombre")
    public Concentracion getByName(@RequestParam("name") String name) {
        return concentracionService.getByName(name);
    }
}