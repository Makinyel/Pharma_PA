package com.example.pharma.domain.service.producto;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.pharma.domain.entities.producto.Bodega;
import com.example.pharma.infrastructure.repository.producto.BodegaRepository;
import com.example.pharma.share.NotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BodegaService {
    private BodegaRepository bodegaRepository;

    public List<Bodega> getBodega() {

        return bodegaRepository.findAll();
    }

    // seleccionar los datos por el ID
    public Optional<Bodega> getBodega(Long id) {
        return bodegaRepository.findById(id);
    }

    public Bodega getBodegaByName(String name) {
        Optional<Bodega> bodega = bodegaRepository.findByNombre(name);
        return bodega.orElseThrow(() -> new NotFoundException("Bodega with name: " + name + " was not found."));
    }

    public void save(Bodega bodega) {
        bodegaRepository.save(bodega);
    }

    public void delete(Long id) {
        bodegaRepository.deleteById(id);
    }

}
