package com.example.pharma.domain.entities.Usuario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

// ESTA ANOTACION LA USE PARA EVITAR EL ERROR DE SERIALIZACION AL OBTENER UNA PERSONA POR SU ID
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Rol implements Serializable {
    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private UserRol tiporol;

}
