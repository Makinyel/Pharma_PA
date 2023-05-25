package com.example.pharma.domain.entities.traslado;

import com.example.pharma.domain.entities.product.Warehouse;
import com.example.pharma.domain.entities.product.Product;
import com.example.pharma.domain.entities.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "traslado")

public class Traslado {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long code;
  private LocalDate fecha;
  private int cantidad;

  @ManyToOne
  @JoinColumn(name = "id_producto", referencedColumnName = "id")
  private Product product;

  @ManyToOne
  @JoinColumn(name = "id_bodegaOrigen", referencedColumnName = "id")
  private Warehouse warehouseOrigen;
  @ManyToOne
  @JoinColumn(name = "id_bodegaDestino", referencedColumnName = "id")
  private Warehouse warehouseDestino;
  @ManyToOne
  @JoinColumn(name = "id_user", referencedColumnName = "id")
  private User user;

}
