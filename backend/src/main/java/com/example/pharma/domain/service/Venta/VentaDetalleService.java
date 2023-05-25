package com.example.pharma.domain.service.Venta;

import com.example.pharma.domain.service.product.WarehouseService;
import com.example.pharma.domain.service.product.ProductService;
import com.example.pharma.domain.service.stock.StockService;
import com.example.pharma.infrastructure.repository.Venta.VentaDetalleRepository;
import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
public class VentaDetalleService {

  private VentaDetalleRepository ventaDetalleRepository;
  private ProductService productService;
  private WarehouseService warehouseService;
  private StockService stockService;
  private VentaService ventaService;

}
