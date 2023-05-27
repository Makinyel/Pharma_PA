package com.example.pharma.infrastructure.api.mapper;

import com.example.pharma.domain.entities.sale.Sale;
import com.example.pharma.infrastructure.api.response.SaleResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SaleResponseMapper {

  @Mapping(target = "client", source = "client.name")
  @Mapping(target = "user", source = "user.name")
  SaleResponse toResponse(Sale sale);
}
