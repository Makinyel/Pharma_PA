package com.example.pharma.domain.service.producto;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.pharma.domain.entities.producto.Concentracion;
import com.example.pharma.infrastructure.repository.producto.ConcentracionRepository;
import com.example.pharma.share.NotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConcentracionService {
    private ConcentracionRepository concentracionRepository;

    public Concentracion guardar(Concentracion concentracion) {
        return concentracionRepository.save(concentracion);
    }

    public List<Concentracion> getAll() {
        return concentracionRepository.findAll();
    }

    public Concentracion getConcentracionByName(String name) {
        Optional<Concentracion> concentracion = concentracionRepository.findByNombre(name);
        return concentracion
                .orElseThrow(() -> new NotFoundException("Concentracion with name: " + name + " was not found."));
    }
}
