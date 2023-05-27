package com.example.pharma.infrastructure.api.mapper;

import com.example.pharma.domain.entities.product.Product;
import com.example.pharma.infrastructure.api.response.ProductResponse;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductResponseMapper {

  @Mapping(target = "brandName", source = "brand.name")
  @Mapping(target = "preparationName", source = "preparation.name")
  @Mapping(target = "concentrationName", source = "concentration.name")
  ProductResponse toResponse(Product product);

  List<ProductResponse> toResponse(List<Product> products);
}
