package com.example.pharma.infrastructure.api.controller.producto;

import com.example.pharma.domain.entities.producto.Bodega;
import com.example.pharma.domain.service.producto.BodegaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/warehouse")
public class BodegaController {

    private BodegaService bodegaService;

    @GetMapping
    public List<Bodega> getAll() {
        return bodegaService.getAll();
    }

    @GetMapping("/detail")
    public Bodega getById(@RequestHeader("id") Long id) {
        return bodegaService.getById(id);
    }

    @GetMapping("/nombre")
    public Bodega getByName(@RequestParam("name") String name) {
        return bodegaService.getByName(name);
    }

    @PostMapping
    public Bodega save(@RequestBody Bodega bodega) {
        bodegaService.save(bodega);
        return bodega;
    }

    @DeleteMapping("/delete")
    public void delete(@RequestHeader("id") Long id) {
        bodegaService.delete(id);
    }
}