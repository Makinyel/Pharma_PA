package com.example.pharma.infrastructure.api.controller.sale;


import com.example.pharma.domain.service.sale.SaleDetailService;
import com.example.pharma.infrastructure.api.mapper.SaleResponseMapper;
import com.example.pharma.infrastructure.api.request.sale.SaleDetailRequest;
import com.example.pharma.infrastructure.api.response.SaleResponse;
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

  private final SaleDetailService saleDetailService;
  private final SaleResponseMapper responseMapper;

  @PostMapping
  public SaleResponse create(@RequestBody List<SaleDetailRequest> saleDetailRequestList) {
    return responseMapper.toResponse(
        saleDetailService.completeSale(saleDetailService.mapper(saleDetailRequestList)));
  }
}
