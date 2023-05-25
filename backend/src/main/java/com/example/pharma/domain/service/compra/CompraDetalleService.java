package com.example.pharma.domain.service.compra;

import com.example.pharma.domain.entities.compra.Compra;
import com.example.pharma.domain.entities.compra.CompraDetalle;
import com.example.pharma.domain.entities.product.Warehouse;
import com.example.pharma.domain.entities.product.Product;
import com.example.pharma.domain.entities.stock.Stock;
import com.example.pharma.domain.service.product.WarehouseService;
import com.example.pharma.domain.service.product.ProductService;
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
  private ProductService productService;
  private WarehouseService warehouseService;
  private CompraService compraService;
  private StockService stockService;

  public CompraDetalle create(CompraDetalle compraDetalle) {

    Product product = productService.findById(compraDetalle.getProduct().getId());
    Warehouse warehouse = warehouseService.findById(compraDetalle.getWarehouseDestino().getId());
    Compra compra = compraService.getById(compraDetalle.getCompra().getCode());
    Double total = product.getBuyingPrice() * compraDetalle.getAmount();

    compraDetalle.setProduct(product);
    compraDetalle.setWarehouseDestino(warehouse);
    compraDetalle.setCompra(compra);
    compraDetalle.setTotal(total);

    Stock stock = Stock.builder()
        .id_producto(product.getId())
        .id_bodega(warehouse.getId())
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
    Compra invoiceUpdate = compraService.getById(compraDetalleList.get(0).getCompra().getCode());
    compraDetalleList.stream()
        .forEach(invoiceDetail -> buildpurchase(invoiceDetail, invoiceUpdate));
    compraService.editCompra(invoiceUpdate);
  }


  private void buildpurchase(CompraDetalle compraDetalle, Compra compraUpdate) {

    create(compraDetalle);

    Product product = productService.findById(compraDetalle.getProduct().getId());

    Double subtotalProduct = compraDetalle.getAmount() * product.getBuyingPrice();

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
        compraDetalle.getWarehouseDestino().getId(),
        compraDetalle.getTotal(),
        compraDetalle.getProduct().getId()
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

    Warehouse warehouse = warehouseService.findByName(request.getDestinationWarehouseName());
    Product product = productService.findByName(request.getProductName());
    Compra compra = compraService.getById(request.getPurchaseId());

    detalle.setAmount(request.getQuantity());
    detalle.setWarehouseDestino(warehouse);
    detalle.setProduct(product);
    detalle.setCompra(compra);

    return detalle;
  }
}

