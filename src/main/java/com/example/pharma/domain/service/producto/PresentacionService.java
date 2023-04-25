package com.example.pharma.domain.service.producto;

import com.example.pharma.domain.entities.producto.Presentacion;
import com.example.pharma.infrastructure.repository.producto.PresentacionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PresentacionService {
    private PresentacionRepository presentacionRepository;

    //seleccionar todos los datos
    public List<Presentacion> getPresentacion() {
        return presentacionRepository.findAll();
    }

    public void guardar(Presentacion presentacion) {
        presentacionRepository.save(presentacion);
    }

    public void delete(String id) {
        presentacionRepository.deleteById(id);
    }
}
