package com.example.pharma.domain.entities.Usuario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

// ESTA ANOTACION LA USE PARA EVITAR EL ERROR DE SERIALIZACION AL OBTENER UNA PERSONA POR SU ID
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Usuario {
    @Id
    private String id;
    private String nombre;
    private String apellido;
    @OneToOne
    private Rol rol;
    private String password;
    private String ubicacion;
    private String telefono;
    private String email;
    @Enumerated(EnumType.STRING)
    private TipoEstados estado;
    private String id_rol;
}
