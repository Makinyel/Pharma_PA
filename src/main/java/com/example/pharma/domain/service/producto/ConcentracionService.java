package com.example.pharma.domain.service.producto;


import com.example.pharma.infrastructure.repository.producto.ConcentracionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.pharma.domain.entities.producto.Concentracion;

@Service
@AllArgsConstructor
public class ConcentracionService {
    private ConcentracionRepository concentracionRepository;

    public void guardar(Concentracion concentracion) {
        concentracionRepository.save(concentracion);
    }
}
