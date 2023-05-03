package com.example.pharma.domain.entities.producto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "maestro_presentacion")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Presentacion {
    @Id
    @Column(name = "id", unique = true)
    private String id;
    private String nombre;
}
