package com.example.pharma.infrastructure.api.controller.stock;

import com.example.pharma.domain.service.stock.StockService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/stock")
public class StockController {

  private final StockService stockService;

}
