package com.example.pharma.infrastructure.api.controller.sale;


import com.example.pharma.domain.service.sale.SaleDetailService;
import com.example.pharma.infrastructure.api.request.sale.SaleDetailRequest;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/sale-details")
public class SaleDetailController {

  private SaleDetailService saleDetailService;

  @PostMapping
  public void create(@RequestBody List<SaleDetailRequest> saleDetailRequestList) {
    saleDetailService.completeSale(saleDetailService.mapper(saleDetailRequestList));
  }
}
