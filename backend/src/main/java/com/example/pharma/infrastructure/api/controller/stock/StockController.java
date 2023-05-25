package com.example.pharma.infrastructure.api.controller.stock;

import com.example.pharma.domain.entities.stock.Stock;
import com.example.pharma.domain.service.stock.StockService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/stock")
public class StockController {

  private final StockService stockService;

}
