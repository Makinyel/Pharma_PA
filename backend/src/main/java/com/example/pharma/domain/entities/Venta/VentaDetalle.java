package com.example.pharma.domain.entities.Venta;

import com.example.pharma.domain.entities.producto.Bodega;
import com.example.pharma.domain.entities.producto.Producto;
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
@Table(name = "ventaDetalle")
public class VentaDetalle {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Integer amount;
  private double total;
  @ManyToOne
  @JoinColumn(name = "id_BodegaOrigen", referencedColumnName = "id")
  private Bodega bodegaOrigen;
  @ManyToOne
  @JoinColumn(name = "id_producto", referencedColumnName = "id")
  private Producto producto;
  @ManyToOne
  @JoinColumn(name = "id_venta", referencedColumnName = "code")
  private Venta venta;

}

