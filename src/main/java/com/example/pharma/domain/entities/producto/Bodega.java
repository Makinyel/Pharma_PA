package com.example.pharma.domain.entities.producto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data //esta dependencia es para los getters and setters
@Entity
@Table(name = "maestro_bodega")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Bodega {
    @Id
    @Column(name = "id", unique = true)
    private String id;
    private String razon_social;
    private String ubicacion;
    private String telefono;
    private String email;
}
