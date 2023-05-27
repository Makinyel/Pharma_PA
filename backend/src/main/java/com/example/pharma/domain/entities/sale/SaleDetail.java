package com.example.pharma.domain.entities.sale;

import com.example.pharma.domain.entities.product.Product;
import com.example.pharma.domain.entities.product.Warehouse;
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
@Table(name = "detalle_venta")
public class SaleDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Integer quantity;
  private double total;
  @ManyToOne
  @JoinColumn(name = "source_warehouse_id", referencedColumnName = "id")
  private Warehouse sourceWarehouse;
  @ManyToOne
  @JoinColumn(name = "product_id", referencedColumnName = "id")
  private Product product;
  @ManyToOne
  @JoinColumn(name = "sale_id", referencedColumnName = "id")
  private Sale sale;
}
