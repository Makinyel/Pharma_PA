package com.example.pharma.domain.service.product;

import com.example.pharma.domain.entities.product.Warehouse;
import com.example.pharma.infrastructure.repository.producto.WarehouseRepository;
import com.example.pharma.shared.NotFoundException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WarehouseService {

  private WarehouseRepository warehouseRepository;

  public List<Warehouse> findAll() {
    return warehouseRepository.findAll();
  }

  public Warehouse findById(Long id) {
    return warehouseRepository
        .findById(id)
        .orElseThrow(() ->
            new NotFoundException("Warehouse with ID: " + id + " was not found"));
  }

  public Warehouse findByName(String name) {
    return warehouseRepository
        .findByName(name)
        .orElseThrow(() ->
            new NotFoundException("Warehouse with name: " + name + " was not found"));
  }

  public void save(Warehouse warehouse) {
    warehouseRepository.save(warehouse);
  }

  public void delete(Long id) {
    warehouseRepository.deleteById(id);
  }

}
