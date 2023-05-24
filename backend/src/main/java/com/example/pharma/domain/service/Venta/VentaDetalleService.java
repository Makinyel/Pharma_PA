package com.example.pharma.domain.service.Venta;

import com.example.pharma.domain.service.producto.BodegaService;
import com.example.pharma.domain.service.producto.ProductoService;
import com.example.pharma.domain.service.stock.StockService;
import com.example.pharma.infrastructure.repository.Venta.VentaDetalleRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
public class VentaDetalleService {

  private VentaDetalleRepository ventaDetalleRepository;
  private ProductoService productoService;
  private BodegaService bodegaService;
  private StockService stockService;
  private VentaService ventaService;

}
