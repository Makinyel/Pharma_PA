package com.example.pharma.domain.entities.compra;

import com.example.pharma.domain.entities.producto.Producto;
import jakarta.persistence.Entity;
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
  private String id;
  private Integer Cantidad;
  private double iva;
  private double total;
  private double totalConIva;
  @ManyToOne
  @JoinColumn(name = "id_producto", referencedColumnName = "id")
  private Producto producto;
  @ManyToOne
  @JoinColumn(name = "id_compra", referencedColumnName = "code")
  private Compra compra;
}

