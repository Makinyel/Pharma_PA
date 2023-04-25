package com.example.pharma.domain.entities.producto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Producto {
    @Id
    @Column(name = "id", unique = true)
    private String id;
    private String nombre;
    private String descripcion;
    private Double precioCosto;
    private Double precioVenta;
    private Double precioVentaDistribuidores;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "idMarca", referencedColumnName = "id"),
    })
    private Marca marca;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "idPresentacion", referencedColumnName = "id"),
    })
    private Presentacion presentacion;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "idBodega", referencedColumnName = "id"),
    })
    private Bodega bodega;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "idConcentracion", referencedColumnName = "id"),
    })
    private Concentracion concentracion;


}
