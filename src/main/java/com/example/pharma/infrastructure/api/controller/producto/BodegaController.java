package com.example.pharma.infrastructure.api.controller.producto;


import com.example.pharma.domain.entities.producto.Bodega;
import com.example.pharma.domain.service.producto.BodegaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/bodega")
public class BodegaController {

    private BodegaService bodegaService;

    //controlador para obtener datos
    @GetMapping
    public List<Bodega> getAll() {

        return bodegaService.getBodega();
    }

    //controlador para obtener datos por una ID
    @GetMapping("/{id}")
    public Optional<Bodega> getBId(@PathVariable("id") String id) {

        return bodegaService.getBodega(id);
    }

    //controlador para ingresar datos
    @PostMapping
    public Bodega saveUpdate(@RequestBody Bodega bodega) {
        bodegaService.save(bodega);
        return bodega;
    }

    //controlador para eliminar datos
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        bodegaService.delete(id);
    }
}
