package com.example.pharma.domain.service.product;

import com.example.pharma.domain.entities.product.Preparation;
import com.example.pharma.infrastructure.repository.producto.PreparationRepository;
import com.example.pharma.shared.NotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PreparationService {

  private PreparationRepository preparationRepository;

  public List<Preparation> findAll() {
    return preparationRepository.findAll();
  }

  public Preparation findByName(String name) {
    Optional<Preparation> preparation = preparationRepository.findByName(name);
    return preparation.orElseThrow(
        () -> new NotFoundException("Preparation with name: " + name + " was not found"));
  }

  public Preparation save(Preparation preparation) {
    return preparationRepository.save(preparation);
  }

  public void delete(Long id) {
    preparationRepository.deleteById(id);
  }
}
