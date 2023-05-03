package com.example.pharma.domain.service.producto;

import com.example.pharma.domain.entities.producto.Marca;
import com.example.pharma.infrastructure.repository.producto.MarcaRepository;
import com.example.pharma.share.NotFoundException;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MarcaService {

  private MarcaRepository marcaRepository;

  public List<Marca> getMarca() {
    return marcaRepository.findAll();
  }

  public Marca agregar(Marca marca) {
    return marcaRepository.save(marca);
  }

  public void delete(String id) {
    marcaRepository.deleteById(id);
  }

  public void editMarca(Marca marca) {
    var marcadetalles = marcaRepository.getById(marca.getId());

    if (!Objects.isNull(marcadetalles)) {
      marcadetalles.setNombre(marca.getNombre());
      marcaRepository.save(marcadetalles);

    }else {
      new NotFoundException("Marca con el Id " + marca.getId() + " Not found");
    }
  }
}
