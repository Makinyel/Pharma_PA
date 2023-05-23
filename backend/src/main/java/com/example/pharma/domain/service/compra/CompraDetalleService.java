package com.example.pharma.domain.service.compra;

import com.example.pharma.domain.entities.compra.Compra;
import com.example.pharma.domain.entities.compra.CompraDetalle;
import com.example.pharma.domain.entities.producto.Bodega;
import com.example.pharma.domain.entities.producto.Producto;
import com.example.pharma.domain.service.stock.StockService;
import com.example.pharma.domain.service.producto.BodegaService;
import com.example.pharma.domain.service.producto.ProductoService;
import com.example.pharma.infrastructure.api.request.compra.CompraDetalleRequest;
import com.example.pharma.infrastructure.repository.compra.CompraDetalleRepository;
import com.example.pharma.shared.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
  public CompraDetalle create(CompraDetalle compraDetalle){

    CompraDetalle detalle = new CompraDetalle();

    detalle.setAmount(detalle.getAmount());

    var producto = productoService.getById(compraDetalle.getProducto().getId());
    var bodega = bodegaService.getById(compraDetalle.getBodegaDestino().getId());
    var compra = compraService.getById(compraDetalle.getCompra().getCode());

    if (Objects.isNull(producto)){
      new NotFoundException("Producto not found");
    }
    if (Objects.isNull(bodega)){
      new NotFoundException("Bodega not found");
    }
    if (Objects.isNull(compra)){
      new NotFoundException("Compra not found");
    }

    var stock = stockService.getByIdProductIdBodega(producto.getId(),bodega.getId());

    System.out.println("STOCK---> " + stock.toString());

    if (Objects.isNull(stock)){
      new NotFoundException("Stock not found");
    }

    stockService.sumarStockMovimientos(stock,compraDetalle.getAmount());

    detalle.setAmount(compraDetalle.getAmount());
    detalle.setProducto(producto);
    detalle.setBodegaDestino(bodega);
    detalle.setCompra(compra);

    var total = producto.getPrecioCosto() * compraDetalle.getAmount();

    detalle.setTotal(total);



    return compraDetalleRepository.save(detalle);
  }

  public void comprarProducto(List<CompraDetalle> compraDetalleList){
    System.out.println("Cantidad -->"+compraDetalleList.get(0).getAmount());
    Compra invoiceUpdate = compraService.getById(compraDetalleList.get(0).getCompra().getCode());
    compraDetalleList.stream().forEach(invoiceDetail -> buildpurchase(invoiceDetail,invoiceUpdate));
    compraService.editCompra(invoiceUpdate);
  }


  private void buildpurchase(CompraDetalle compraDetalle, Compra compraUpdate) {

    create(compraDetalle);

    var product = productoService.getById(compraDetalle.getProducto().getId());

    var subtotalProduct = compraDetalle.getAmount() * product.getPrecioCosto();

    var total = compraUpdate.getTotal() + subtotalProduct;
    var totalWithIva = compraUpdate.getTotalWithIva() + (subtotalProduct + (subtotalProduct * 0.19));
    var ivaInvoice = totalWithIva - total;

    compraUpdate.setTotal(total);
    compraUpdate.setTotalWithIva(totalWithIva);
    compraUpdate.setIva(ivaInvoice);

  }
  public List<CompraDetalle> getAll(){
    return compraDetalleRepository.findAll();
  }


  public CompraDetalle getById(Long id){
    return compraDetalleRepository.getById(id);
  }

  public void edit(CompraDetalle compraDetalle){
    compraDetalleRepository.editCompraDetalle(
        compraDetalle.getAmount(),
        compraDetalle.getBodegaDestino().getId(),
        compraDetalle.getTotal(),
        compraDetalle.getProducto().getId()
    );
  }
  public void delete(Long id){
    compraDetalleRepository.deleteById(id);
  }

  public void asignarIdCompras(List<CompraDetalle> list,Long id){
    list.stream().forEach(compraDetalle -> aggCompra(compraDetalle,id));
  }

  private void aggCompra(CompraDetalle compraDetalle,Long id_compra){
    var compra = compraService.getById(id_compra);
    compraDetalle.setCompra(compra);
  }



  public List<CompraDetalle> mapper(List<CompraDetalleRequest> compraDetalleRequest){

    List<CompraDetalle> compraDetalleList = new ArrayList();


    for ( CompraDetalleRequest responsible1 : compraDetalleRequest ) {
      compraDetalleList.add(build(responsible1));
    }
    return  compraDetalleList;
  }
  public CompraDetalle build(CompraDetalleRequest request){
    if (request == null){
      return null;
    }
    CompraDetalle detalle = new CompraDetalle();

    Bodega bodega = bodegaService.getByName(request.getBodegaDestino());
    Producto producto = productoService.findByName(request.getProducto());
    Compra compra = compraService.getById(request.getId_compra());

    detalle.setAmount(request.getAmount());
    detalle.setBodegaDestino(bodega);
    detalle.setProducto(producto);
    detalle.setCompra(compra);

    System.out.println("DETALLE--> " + detalle.toString());

    return detalle;
  }


}

