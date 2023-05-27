package com.example.pharma.infrastructure.api.mapper;

import com.example.pharma.domain.entities.purchase.Purchase;
import com.example.pharma.infrastructure.api.response.PurchaseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PurchaseResponseMapper {

  @Mapping(target = "provider", source = "provider.name")
  @Mapping(target = "user", source = "user.name")
  PurchaseResponse toResponse(Purchase purchase);
}
