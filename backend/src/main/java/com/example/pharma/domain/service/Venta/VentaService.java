package com.example.pharma.domain.service.Venta;

import com.example.pharma.domain.entities.Venta.Venta;
import com.example.pharma.infrastructure.repository.Venta.VentaRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class VentaService {

  private final VentaRepository ventaRepository;


  public Venta create(Venta ventaDetails){

    Venta venta = new Venta();

    venta.setIva(0);
    venta.setTotal(0);
    venta.setTotalWithIva(0);
    venta.setFecha(LocalDate.now());


    return ventaRepository.save(venta);
  }

}
