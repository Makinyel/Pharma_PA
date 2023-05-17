package com.example.pharma.domain.entities.stock;

import com.example.pharma.domain.entities.Keys.KeyStock;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "stock")
@IdClass(KeyStock.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Stock implements Serializable {
  @Id
  private Long id_producto;
  @Id
  private Long id_bodega;
  private Integer cantidad;
}
