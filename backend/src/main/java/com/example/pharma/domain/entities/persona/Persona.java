package com.example.pharma.domain.entities.persona;

import com.example.pharma.domain.entities.keys.KeyPersona;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@IdClass(KeyPersona.class)
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
