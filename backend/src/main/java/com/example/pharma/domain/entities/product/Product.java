package com.example.pharma.domain.entities.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "maestro_producto")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String description;
  private Double buyingPrice;
  private Double sellingPrice;
  @ManyToOne
  @JoinColumn(name = "brand_id", referencedColumnName = "id")
  private Brand brand;
  @ManyToOne
  @JoinColumn(name = "presentation_id", referencedColumnName = "id")
  private Preparation preparation;
  @ManyToOne
  @JoinColumn(name = "concentration_id", referencedColumnName = "id")
  private Concentration concentration;
}