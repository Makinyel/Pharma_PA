package com.example.pharma.domain.entities.compra;

import com.example.pharma.domain.entities.persona.Persona;
import com.example.pharma.domain.entities.producto.Bodega;
import com.example.pharma.domain.entities.usuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "compra")
public class Compra{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    private LocalDate fecha;
    private String codeFactura;
    private double total;
    private double totalWithIva;
    private double iva;
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "idPovedor", referencedColumnName = "id"),
        @JoinColumn(name = "typeIdProveedor", referencedColumnName = "tipoId")})
    private Persona proveedor;
    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "id")
    private Usuario usuario;
}