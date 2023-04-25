package com.example.pharma.domain.entities.Keys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class KeyPersona implements Serializable {
    String id;
    String tipoId;
}
