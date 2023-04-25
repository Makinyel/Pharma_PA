package com.example.pharma.domain.entities.producto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "maestro_marca")
public class Marca {
    @Id
    @Column(name = "id", unique = true)
    private String id;
    private String nombre;
}
