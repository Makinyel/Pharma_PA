package com.example.pharma.infrastructure.api.controller.product;

import com.example.pharma.domain.entities.product.Preparation;
import com.example.pharma.domain.service.product.PreparationService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/preparation")
public class PreparationController {

  private PreparationService preparationService;

  @PostMapping
  public Preparation save(@RequestBody Preparation preparation) {
    return preparationService.save(preparation);
  }

  @GetMapping
  public List<Preparation> findAll() {
    return preparationService.findAll();
  }

  @GetMapping("name")
  public Preparation findByName(@RequestParam("name") String name) {
    return preparationService.findByName(name);
  }

  @DeleteMapping("{id}")
  public void delete(@PathVariable("id") Long id) {
    preparationService.delete(id);
  }
}
