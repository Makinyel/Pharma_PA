package com.example.pharma.domain.service;

import com.example.pharma.domain.entities.keys.KeyStock;
import com.example.pharma.domain.entities.stock.Stock;
import com.example.pharma.domain.entities.stock.Traslado;
import com.example.pharma.domain.entities.producto.Bodega;
import com.example.pharma.domain.entities.producto.Producto;
import com.example.pharma.domain.entities.usuario.Usuario;
import com.example.pharma.domain.service.producto.BodegaService;
import com.example.pharma.domain.service.producto.ProductoService;
import com.example.pharma.infrastructure.api.request.TrasladoRequest;
import com.example.pharma.infrastructure.repository.StockRepository;
import com.example.pharma.infrastructure.repository.TrasladoRepository;
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
  private final BodegaService bodegaService;
  private final ProductoService productoService;

  public Traslado create(TrasladoRequest trasladoRequest) {

    // Obtenemos Bodegas
    Bodega bodegaOrigen = bodegaService.getByName(trasladoRequest.getBodegaOrigen());
    Bodega bodegaDestino = bodegaService.getByName(trasladoRequest.getBodegaDestino());

    // Construimos las Key de los Stock
    KeyStock keyStockOrigin = KeyStock.builder()
        .id_producto(Long.parseLong(trasladoRequest.getProducto())).id_bodega(bodegaOrigen.getId()).build();

    KeyStock keyStockDestination = KeyStock.builder()
        .id_producto(Long.parseLong(trasladoRequest.getProducto()))
        .id_bodega(bodegaDestino.getId()).build();

    // Obtenemos Los Stocks
    Optional<Stock> stockOptional = stockRepository.findById(keyStockOrigin);
    Optional<Stock> stockOptionalDestination = stockRepository.findById(keyStockDestination);

    // Verificamos Si el Stock De Destino Exite
    if (!stockOptionalDestination.isPresent()) {

      Stock stock = stockOptional.get();
      stockService.restarStock(stock, trasladoRequest.getCantidad());

      Stock stockDestination = Stock.builder()
          .id_producto(productoService.getById(Long.parseLong(trasladoRequest.getProducto())).getId())
          .id_bodega((bodegaService.getByName(trasladoRequest.getBodegaDestino()).getId()))
          .cantidad(trasladoRequest.getCantidad())
          .build();

      stockService.create(stockDestination);

    } else {

      Stock stock = stockOptional.get();
      Stock stockDestination = stockOptionalDestination.get();

      stockService.restarStock(stock, trasladoRequest.getCantidad());
      stockService.sumarStockMovimientos(stockDestination, trasladoRequest.getCantidad());
    }

    Traslado traslado = new Traslado();
    traslado.setFecha(LocalDate.now());
    traslado.setCantidad(trasladoRequest.getCantidad());

    Producto producto = productoService.getById(Long.parseLong(trasladoRequest.getProducto()));
    Usuario usuario = usuarioService.getUsuarioById(Long.parseLong(trasladoRequest.getUser()));

    traslado.setProducto(producto);
    traslado.setBodegaOrigen(bodegaOrigen);
    traslado.setBodegaDestino(bodegaDestino);
    traslado.setUser(usuario);

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