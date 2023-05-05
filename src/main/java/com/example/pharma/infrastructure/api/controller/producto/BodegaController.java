package com.example.pharma.infrastructure.api.controller.producto;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pharma.domain.entities.producto.Bodega;
import com.example.pharma.domain.service.producto.BodegaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/bodega")
public class BodegaController {

    private BodegaService bodegaService;

    // endpoint para obtener datos
    @GetMapping
    public List<Bodega> getAll() {
        return bodegaService.getBodega();
    }

    // endpoint para obtener datos por una ID
    @GetMapping("/detail")
    public Optional<Bodega> getById(@RequestHeader("id") Long id) {
        return bodegaService.getBodega(id);
    }

    @GetMapping("/nombre")
    public Bodega getBodegaByNombre(@RequestParam("name") String name) {
        return bodegaService.getBodegaByName(name);
    }

    // endpoint para ingresar datos
    @PostMapping
    public Bodega saveUpdate(@RequestBody Bodega bodega) {
        bodegaService.save(bodega);
        return bodega;
    }

    // endpoint para eliminar datos
    @DeleteMapping("/delete")
    public void delete(@RequestHeader("id") Long id) {
        bodegaService.delete(id);
    }
}