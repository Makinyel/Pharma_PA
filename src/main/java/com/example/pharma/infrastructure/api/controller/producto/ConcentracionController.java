package com.example.pharma.infrastructure.api.controller.producto;

import com.example.pharma.domain.entities.producto.Concentracion;
import com.example.pharma.domain.service.producto.ConcentracionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}