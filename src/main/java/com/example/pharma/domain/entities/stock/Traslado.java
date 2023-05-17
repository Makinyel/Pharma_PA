package com.example.pharma.domain.entities.stock;

import com.example.pharma.domain.entities.producto.Bodega;
import com.example.pharma.domain.entities.producto.Producto;
import com.example.pharma.domain.entities.usuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Date;
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
  private Producto producto;
  @ManyToOne
  @JoinColumn(name = "id_bodegaOrigen", referencedColumnName = "id")
  private Bodega bodegaOrigen;
  @ManyToOne
  @JoinColumn(name = "id_bodegaDestino", referencedColumnName = "id")
  private Bodega bodegaDestino;
  @ManyToOne
  @JoinColumn(name = "id_user", referencedColumnName = "id")
  private Usuario user;

}
