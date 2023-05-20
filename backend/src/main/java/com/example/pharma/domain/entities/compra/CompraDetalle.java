package com.example.pharma.domain.entities.compra;

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
import org.hibernate.annotations.IdGeneratorType;

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
  private Bodega bodegaDestino;
  @ManyToOne
  @JoinColumn(name = "id_producto", referencedColumnName = "id")
  private Producto producto;
  @ManyToOne
  @JoinColumn(name = "id_compra", referencedColumnName = "code")
  private Compra compra;
}

