package com.example.pharma.infrastructure.api.mapper;

import com.example.pharma.domain.entities.product.Product;
import com.example.pharma.infrastructure.api.response.ProductResponse;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductResponseMapper {

  @Mapping(target = "brand", source = "brand.name")
  @Mapping(target = "preparation", source = "preparation.name")
  @Mapping(target = "concentration", source = "concentration.name")
  @Mapping(target = "prefix", source = "concentration.prefix")
  ProductResponse toResponse(Product product);

  List<ProductResponse> toResponse(List<Product> products);
}
