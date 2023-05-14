package com.example.pharma.domain.entities.keys;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class KeyStock implements Serializable {

    private Long id_producto;
    private Long id_bodega;
}
