package com.example.pharma.domain.service.product;

import com.example.pharma.domain.entities.product.Concentration;
import com.example.pharma.infrastructure.repository.producto.ConcentrationRepository;
import com.example.pharma.shared.NotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConcentrationService {

  private ConcentrationRepository concentrationRepository;

  public Concentration save(Concentration concentration) {
    return concentrationRepository.save(concentration);
  }

  public List<Concentration> findAll() {
    return concentrationRepository.findAll();
  }

  public Concentration findByName(String name) {
    Optional<Concentration> concentration = concentrationRepository.findByName(name);
    return concentration.orElseThrow(
        () -> new NotFoundException("Concentration with name: " + name + " was not found."));
  }
}
