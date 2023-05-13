package com.example.pharma.domain.service.producto;

import com.example.pharma.domain.entities.producto.Marca;
import com.example.pharma.infrastructure.repository.producto.MarcaRepository;
import com.example.pharma.shared.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MarcaService {

  private MarcaRepository marcaRepository;

  public List<Marca> getMarca() {
    return marcaRepository.findAll();
  }

  public Marca getMarcaById(Long id) {
    Optional<Marca> marca = marcaRepository.findById(id);
    return marca.orElseThrow(() -> new NotFoundException("Marca with id: " + id + " not found"));
  }

  public Marca getMarcaByNombre(String nombre) {
    Optional<Marca> marca = marcaRepository.findByNombre(nombre);
    return marca.orElseThrow(() -> new NotFoundException("Marca with nombre: " + nombre + " not found"));
  }

  public Marca agregar(Marca marca) {
    return marcaRepository.save(marca);
  }

  public void delete(Long id) {
    marcaRepository.deleteById(id);
  }

  public void editMarca(Marca marca) {
    var marcadetalles = marcaRepository.getById(marca.getId());

    if (!Objects.isNull(marcadetalles)) {
      marcadetalles.setNombre(marca.getNombre());
      marcaRepository.save(marcadetalles);

    } else {
      new NotFoundException("Marca con el Id " + marca.getId() + " Not found");
    }
  }
}
