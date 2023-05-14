package com.example.pharma.domain.entities.producto;

import jakarta.persistence.*;
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
public class Producto {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String nombre;
        private String descripcion;
        private Double precioCosto;
        private Double precioVenta;
        @ManyToOne
        @JoinColumn(name = "idMarca", referencedColumnName = "id")
        private Marca marca;
        @ManyToOne
        @JoinColumn(name = "idPresentacion", referencedColumnName = "id")
        private Presentacion presentacion;
        @ManyToOne
        @JoinColumn(name = "idConcentracion", referencedColumnName = "id")
        private Concentracion concentracion;
}