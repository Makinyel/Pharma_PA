package com.example.pharma.domain.service.producto;

import com.example.pharma.domain.entities.producto.Bodega;
import com.example.pharma.infrastructure.repository.producto.BodegaRepository;
import com.example.pharma.shared.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BodegaService {

    private BodegaRepository bodegaRepository;

    public List<Bodega> getAll() {
        return bodegaRepository.findAll();
    }

    public Bodega getById(Long id) {
        return bodegaRepository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Warehouse with ID: " + id + " was not found"));
    }

    public Bodega getByName(String name) {
        return bodegaRepository
                .findByNombre(name)
                .orElseThrow(() ->
                        new NotFoundException("Warehouse with name: " + name + " was not found"));
    }

    public void save(Bodega bodega) {
        bodegaRepository.save(bodega);
    }

    public void delete(Long id) {
        bodegaRepository.deleteById(id);
    }

}
