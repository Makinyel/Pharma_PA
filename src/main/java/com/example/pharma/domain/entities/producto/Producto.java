package com.example.pharma.domain.entities.producto;

import com.example.pharma.domain.entities.Stock;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
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
@Table(name = "maestro_producto")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
