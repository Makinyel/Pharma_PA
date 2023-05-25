package com.example.pharma.infrastructure.api.controller.product;

import com.example.pharma.domain.entities.product.Brand;
import com.example.pharma.domain.service.product.BrandService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/brand")
public class BrandController {

  private BrandService brandService;

  @PostMapping
  public Brand save(@RequestBody Brand brand) {
    brandService.save(brand);
    return brand;
  }

  @GetMapping
  public List<Brand> findAll() {
    return brandService.findBrand();
  }

  @GetMapping("{id}")
  public Brand findById(@PathVariable("id") Long id) {
    return brandService.findById(id);
  }

  @GetMapping("name")
  public Brand findByName(@RequestParam("name") String name) {
    return brandService.findByName(name);
  }
}