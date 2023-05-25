package com.example.pharma.domain.entities.stock;

import com.example.pharma.domain.entities.keys.KeyStock;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
@IdClass(KeyStock.class)
@Table(name = "stock")
public class Stock {

  @Id
  private Long productId;
  @Id
  private Long warehouseId;
  private Integer cantidad;
}
