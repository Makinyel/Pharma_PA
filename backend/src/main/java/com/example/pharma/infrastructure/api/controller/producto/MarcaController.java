package com.example.pharma.infrastructure.api.controller.producto;

import com.example.pharma.domain.entities.producto.Marca;
import com.example.pharma.domain.service.producto.MarcaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/brand")
public class MarcaController {
    private MarcaService marcaService;

    @PostMapping
    public Marca save(@RequestBody Marca marca) {
        marcaService.save(marca);
        return marca;
    }

    @GetMapping
    public List<Marca> getAll() {
        return marcaService.getMarca();
    }

    @GetMapping("/id/{id}")
    public Marca getById(@PathVariable("id") Long id) {
        return marcaService.getById(id);
    }

    @GetMapping("nombre")
    public Marca getByNombre(@RequestParam("name") String name) {
        return marcaService.getByName(name);
    }
}