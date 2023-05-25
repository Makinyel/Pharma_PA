package com.example.pharma.domain.service.traslado;

import com.example.pharma.domain.entities.keys.KeyStock;
import com.example.pharma.domain.entities.stock.Stock;
import com.example.pharma.domain.entities.traslado.Traslado;
import com.example.pharma.domain.entities.product.Warehouse;
import com.example.pharma.domain.entities.product.Product;
import com.example.pharma.domain.entities.user.User;
import com.example.pharma.domain.service.usuario.UsuarioService;
import com.example.pharma.domain.service.product.WarehouseService;
import com.example.pharma.domain.service.product.ProductService;
import com.example.pharma.domain.service.stock.StockService;
import com.example.pharma.infrastructure.api.request.traslado.TrasladoRequest;
import com.example.pharma.infrastructure.repository.stock.StockRepository;
import com.example.pharma.infrastructure.repository.traslado.TrasladoRepository;
import com.example.pharma.shared.NotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TrasladoService {

  private final TrasladoRepository trasladoRepository;
  private final StockRepository stockRepository;
  private final StockService stockService;
  private final UsuarioService usuarioService;
  private final WarehouseService warehouseService;
  private final ProductService productService;

  public Traslado create(TrasladoRequest trasladoRequest) {

    // Obtenemos Bodegas
    Warehouse warehouseOrigen = warehouseService.findByName(trasladoRequest.getBodegaOrigen());
    Warehouse warehouseDestino = warehouseService.findByName(trasladoRequest.getBodegaDestino());

    // Construimos las Key de los Stock
    KeyStock keyStockOrigin = KeyStock.builder()
        .productId(Long.parseLong(trasladoRequest.getProducto())).warehouseId(warehouseOrigen.getId()).build();

    KeyStock keyStockDestination = KeyStock.builder()
        .productId(Long.parseLong(trasladoRequest.getProducto()))
        .warehouseId(warehouseDestino.getId()).build();

    // Obtenemos Los Stocks
    Optional<Stock> stockOptional = stockRepository.findById(keyStockOrigin);
    Optional<Stock> stockOptionalDestination = stockRepository.findById(keyStockDestination);

    // Verificamos Sino el Stock De Destino Exite
    if (!stockOptionalDestination.isPresent()) {

      Stock stock = stockOptional.get();
      stockService.restarStockMovimientos(stock, trasladoRequest.getCantidad());

      Stock stockDestination = Stock.builder()
          .id_producto(productService.findById(Long.parseLong(trasladoRequest.getProducto())).getId())
          .id_bodega((warehouseService.findByName(trasladoRequest.getBodegaDestino()).getId()))
          .cantidad(trasladoRequest.getCantidad())
          .build();

      //stockService.create(stockDestination);

    } else {

      Stock stock = stockOptional.get();
      Stock stockDestination = stockOptionalDestination.get();

      stockService.restarStockMovimientos(stock, trasladoRequest.getCantidad());
      stockService.sumarStockMovimientos(stockDestination, trasladoRequest.getCantidad());
    }

    Traslado traslado = new Traslado();
    traslado.setFecha(LocalDate.now());
    traslado.setCantidad(trasladoRequest.getCantidad());

    Product product = productService.findById(Long.parseLong(trasladoRequest.getProducto()));
    User user = usuarioService.getUsuarioById(Long.parseLong(trasladoRequest.getUser()));

    traslado.setProduct(product);
    traslado.setWarehouseOrigen(warehouseOrigen);
    traslado.setWarehouseDestino(warehouseDestino);
    traslado.setUser(user);

    return trasladoRepository.save(traslado);
  }

  public List<Traslado> getAll() {
    return trasladoRepository.findAll();
  }

  public Traslado getById(Long id) {
    Traslado traslado = trasladoRepository.getById(id);
    if (Objects.isNull(traslado)) {
      new NotFoundException("Traslado not found");
    }
    return traslado;
  }

}