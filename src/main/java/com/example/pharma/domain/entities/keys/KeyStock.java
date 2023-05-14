package com.example.pharma.domain.entities.keys;

import com.example.pharma.domain.entities.producto.Bodega;
import com.example.pharma.domain.entities.producto.Producto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class KeyStock implements Serializable {

    private Long id_producto;
    private Long id_bodega;
}
