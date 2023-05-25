package com.example.pharma.domain.entities.compra;

import com.example.pharma.domain.entities.persona.Persona;
import com.example.pharma.domain.entities.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
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
    private LocalDate date;
    private String codeInvoice;
    private double total;
    private double totalWithIva;
    private double iva;
    @ManyToOne
    @JoinColumn(name = "providerId", referencedColumnName = "id")
    private Persona provider;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;
}