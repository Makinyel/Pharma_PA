package com.example.pharma.domain.service.product;

import com.example.pharma.domain.entities.product.Brand;
import com.example.pharma.domain.entities.product.Concentration;
import com.example.pharma.domain.entities.product.Preparation;
import com.example.pharma.domain.entities.product.Product;
import com.example.pharma.infrastructure.api.request.product.ProductRequest;
import com.example.pharma.infrastructure.repository.producto.ProductRepository;
import com.example.pharma.shared.NotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class ProductService {

  private final ProductRepository productRepository;
  private final BrandService brandService;
  private final PreparationService preparationService;
  private final ConcentrationService concentrationService;

  public Product save(ProductRequest productRequest) {
    Product product = new Product();
    product.setName(productRequest.getName());
    product.setDescription(productRequest.getDescription());
    product.setBuyingPrice(productRequest.getBuyingPrice());
    product.setSellingPrice(productRequest.getSellingPrice());

    // Retrieve the related entities by name
    Brand brand = brandService.findByName(productRequest.getBrand());
    Preparation preparation = preparationService.findByName(productRequest.getPresentation());
    Concentration concentration = concentrationService.findByName(
        productRequest.getConcentration());

    // Set the related entities in the product
    product.setBrand(brand);
    product.setPreparation(preparation);
    product.setConcentration(concentration);
    return productRepository.save(product);
  }

  public List<Product> findAll() {
    return productRepository.findAll();
  }

  public Product findById(Long id) {
    Optional<Product> product = productRepository.findById(id);
    return product.orElseThrow(
        () -> new NotFoundException("Product with id: " + id + " not found"));
  }

  public Product findByName(String name) {
    Optional<Product> product = productRepository.findByName(name);
    return product.orElseThrow(
        () -> new NotFoundException("Product with name: " + name + " was not found."));
  }

  public void delete(Long id) {
    productRepository.deleteById(id);
  }
}
