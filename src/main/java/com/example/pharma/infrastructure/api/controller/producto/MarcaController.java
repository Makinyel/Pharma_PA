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

    @GetMapping
    public List<Marca> getAll() {
        return marcaService.getMarca();
    }

    @PostMapping
    public Marca agregar(@RequestBody Marca marca) {
        marcaService.agregar(marca);
        return marca;
    }

}
