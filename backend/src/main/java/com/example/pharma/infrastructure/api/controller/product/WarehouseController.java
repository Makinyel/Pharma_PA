package com.example.pharma.infrastructure.api.controller.product;

import com.example.pharma.domain.entities.product.Warehouse;
import com.example.pharma.domain.service.product.WarehouseService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

  private WarehouseService warehouseService;

  @GetMapping
  public List<Warehouse> finddAll() {
    return warehouseService.findAll();
  }

  @GetMapping("/detail")
  public Warehouse finddById(@RequestHeader("id") Long id) {
    return warehouseService.findById(id);
  }

  @GetMapping("/name")
  public Warehouse finddByName(@RequestParam("name") String name) {
    return warehouseService.findByName(name);
  }

  @PostMapping
  public Warehouse save(@RequestBody Warehouse warehouse) {
    warehouseService.save(warehouse);
    return warehouse;
  }

  @DeleteMapping("/delete")
  public void delete(@RequestHeader("id") Long id) {
    warehouseService.delete(id);
  }
}