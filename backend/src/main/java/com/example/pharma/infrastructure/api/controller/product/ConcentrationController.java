package com.example.pharma.infrastructure.api.controller.product;

import com.example.pharma.domain.entities.product.Concentration;
import com.example.pharma.domain.service.product.ConcentrationService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/concentration")
public class ConcentrationController {

  private ConcentrationService concentrationService;

  @PostMapping
  public Concentration save(@RequestBody Concentration concentration) {
    concentrationService.save(concentration);
    return concentration;
  }

  @GetMapping
  public List<Concentration> findAll() {
    return concentrationService.findAll();
  }

  @GetMapping("/name")
  public Concentration findByName(@RequestParam("name") String name) {
    return concentrationService.findByName(name);
  }
}