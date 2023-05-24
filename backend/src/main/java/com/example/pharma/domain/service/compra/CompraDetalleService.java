package com.example.pharma.domain.service.compra;

import com.example.pharma.domain.entities.compra.Compra;
import com.example.pharma.domain.entities.compra.CompraDetalle;
import com.example.pharma.domain.entities.producto.Bodega;
import com.example.pharma.domain.entities.producto.Producto;
import com.example.pharma.domain.entities.stock.Stock;
import com.example.pharma.domain.service.producto.BodegaService;
import com.example.pharma.domain.service.producto.ProductoService;
import com.example.pharma.domain.service.stock.StockService;
import com.example.pharma.infrastructure.api.request.compra.CompraDetalleRequest;
import com.example.pharma.infrastructure.repository.compra.CompraDetalleRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CompraDetalleService {

  private CompraDetalleRepository compraDetalleRepository;
  private ProductoService productoService;
  private BodegaService bodegaService;
  private CompraService compraService;
  private StockService stockService;

  public CompraDetalle create(CompraDetalle compraDetalle) {

    Producto producto = productoService.getById(compraDetalle.getProducto().getId());
    Bodega bodega = bodegaService.getById(compraDetalle.getBodegaDestino().getId());
    Compra compra = compraService.getById(compraDetalle.getCompra().getCode());
    Double total = producto.getPrecioCosto() * compraDetalle.getAmount();

    compraDetalle.setProducto(producto);
    compraDetalle.setBodegaDestino(bodega);
    compraDetalle.setCompra(compra);
    compraDetalle.setTotal(total);

    Stock stock = Stock.builder()
        .id_producto(producto.getId())
        .id_bodega(bodega.getId())
        .build();

    stockService.create(stock, compraDetalle.getAmount());

    return compraDetalleRepository.save(compraDetalle);
  }

  public List<CompraDetalle> getAll() {
    return compraDetalleRepository.findAll();
  }

  public CompraDetalle getById(Long id) {
    return compraDetalleRepository.getById(id);
  }

  public void comprarProducto(List<CompraDetalle> compraDetalleList) {
    System.out.println("Cantidad -->" + compraDetalleList.get(0).getAmount());
    Compra invoiceUpdate = compraService.getById(compraDetalleList.get(0).getCompra().getCode());
    compraDetalleList.stream()
        .forEach(invoiceDetail -> buildpurchase(invoiceDetail, invoiceUpdate));
    compraService.editCompra(invoiceUpdate);
  }


  private void buildpurchase(CompraDetalle compraDetalle, Compra compraUpdate) {

    create(compraDetalle);

    Producto product = productoService.getById(compraDetalle.getProducto().getId());

    Double subtotalProduct = compraDetalle.getAmount() * product.getPrecioCosto();

    Double total = compraUpdate.getTotal() + subtotalProduct;
    Double totalWithIva =
        compraUpdate.getTotalWithIva() + (subtotalProduct + (subtotalProduct * 0.19));
    Double ivaInvoice = totalWithIva - total;

    compraUpdate.setTotal(total);
    compraUpdate.setTotalWithIva(totalWithIva);
    compraUpdate.setIva(ivaInvoice);

  }

  @Transactional
  public void edit(CompraDetalle compraDetalle) {
    compraDetalleRepository.editCompraDetalle(
        compraDetalle.getAmount(),
        compraDetalle.getBodegaDestino().getId(),
        compraDetalle.getTotal(),
        compraDetalle.getProducto().getId()
    );
  }

  public void delete(Long id) {
    compraDetalleRepository.deleteById(id);
  }

  // Con Estos Metodos Mappeamos de Request a Entity

  public List<CompraDetalle> mapper(List<CompraDetalleRequest> compraDetalleRequest) {

    List<CompraDetalle> compraDetalleList = new ArrayList();

    for (CompraDetalleRequest responsible1 : compraDetalleRequest) {
      compraDetalleList.add(build(responsible1));
    }
    return compraDetalleList;
  }

  public CompraDetalle build(CompraDetalleRequest request) {
    if (request == null) {
      return null;
    }
    CompraDetalle detalle = new CompraDetalle();

    Bodega bodega = bodegaService.getByName(request.getDestinationWarehouseName());
    Producto producto = productoService.findByName(request.getProductName());
    Compra compra = compraService.getById(request.getPurchaseId());

    detalle.setAmount(request.getQuantity());
    detalle.setBodegaDestino(bodega);
    detalle.setProducto(producto);
    detalle.setCompra(compra);

    System.out.println("DETALLE--> " + detalle.toString());

    return detalle;
  }
}

