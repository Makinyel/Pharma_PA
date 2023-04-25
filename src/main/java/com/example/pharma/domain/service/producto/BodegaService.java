package com.example.pharma.domain.service.producto;


import com.example.pharma.domain.entities.producto.Bodega;
import com.example.pharma.infrastructure.repository.producto.BodegaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BodegaService {
    private BodegaRepository bodegaRepository;

    public List<Bodega> getBodega() {

        return bodegaRepository.findAll();
    }

    //seleccionar los datos por el ID
    public Optional<Bodega> getBodega(String id) {
        return bodegaRepository.findById(id);
    }

    public void save(Bodega bodega) {
        bodegaRepository.save(bodega);
    }

    public void delete(String id) {
        bodegaRepository.deleteById(id);
    }

}
