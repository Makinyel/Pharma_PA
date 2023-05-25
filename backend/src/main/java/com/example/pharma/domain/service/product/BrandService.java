package com.example.pharma.domain.service.product;

import com.example.pharma.domain.entities.product.Brand;
import com.example.pharma.infrastructure.repository.producto.BrandRepository;
import com.example.pharma.shared.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BrandService {

  private BrandRepository brandRepository;

  public List<Brand> findBrand() {
    return brandRepository.findAll();
  }

  public Brand findById(Long id) {
    Optional<Brand> brand = brandRepository.findById(id);
    return brand.orElseThrow(() -> new NotFoundException("Brand with id: " + id + " not found"));
  }

  public Brand findByName(String name) {
    Optional<Brand> brand = brandRepository.findByName(name);
    return brand.orElseThrow(() -> new NotFoundException("Brand with name: " + name + " not found"));
  }

  public Brand save(Brand brand) {
    return brandRepository.save(brand);
  }
}
