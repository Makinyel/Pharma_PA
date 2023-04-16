package com.example.pharma.domain.entities.Persona;

import com.example.pharma.domain.entities.Keys.KeyPersona;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

// ESTA ANOTACION LA USE PARA INDICAR QUE ESTA CLASE TENDRA UNA KEY La CUAL ENTRADA
// LOS DOS ATRIBUTOS DE QUE FORMAN LA LLAVE PRIMARIA
@IdClass(KeyPersona.class)

// ESTA ANOTACION LA USE PARA EVITAR EL ERROR DE SERIALIZACION AL OBTENER UNA PERSONA POR SU ID
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Persona {
    @Id
    private String id;
    @Id
    private String tipoId;
    @Enumerated(EnumType.STRING)
    private TipoPersona tipoPersona;
    private String nombre;
    private String ubicacion;
    private String telefono;
    private String email;

}
