package com.example.pharma.domain.service.producto;

import com.example.pharma.domain.entities.producto.Marca;
import com.example.pharma.infrastructure.repository.producto.MarcaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MarcaService {
    private MarcaRepository marcaRepository;

    public List<Marca> getMarca() {
        return marcaRepository.findAll();
    }

    public void agregar(Marca marca) {
        marcaRepository.save(marca);
    }
}
