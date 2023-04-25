package com.example.pharma.domain.entities;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Movimiento {
    @Id
    String id;
    String tipo;
    Date fecha;
    int cantidad;
    Double costoU;
    Double costototal;

}
