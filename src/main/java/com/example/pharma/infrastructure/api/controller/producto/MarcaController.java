package com.example.pharma.infrastructure.api.controller.producto;

import com.example.pharma.domain.entities.producto.Marca;
import com.example.pharma.domain.service.producto.MarcaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/marca")
public class MarcaController {
    private MarcaService marcaService;

    @PostMapping
    public Marca agregar(@RequestBody Marca marca) {
        marcaService.agregar(marca);
        return marca;
    }

    @GetMapping
    public List<Marca> getAll() {
        return marcaService.getMarca();
    }

    @GetMapping("/id/{id}")
    public Marca getMarcaById(@PathVariable("id") Long id) {
        return marcaService.getMarcaById(id);
    }

    @GetMapping("nombre")
    public Marca getMarcaByNombre(@RequestParam("name") String name) {
        return marcaService.getMarcaByNombre(name);
    }

}
