package com.example.pharma.domain.entities.compra;

import com.example.pharma.domain.entities.product.Warehouse;
import com.example.pharma.domain.entities.product.Product;
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
@Table(name = "compraDetalle")
public class CompraDetalle{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Integer amount;
  private double total;
  @ManyToOne
  @JoinColumn(name = "id_BodegaDestino", referencedColumnName = "id")
  private Warehouse warehouseDestino;
  @ManyToOne
  @JoinColumn(name = "id_producto", referencedColumnName = "id")
  private Product product;
  @ManyToOne
  @JoinColumn(name = "id_compra", referencedColumnName = "code")
  private Compra compra;
}

