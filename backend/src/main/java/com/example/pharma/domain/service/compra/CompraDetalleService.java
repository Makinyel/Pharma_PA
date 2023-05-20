package com.example.pharma.domain.service.compra;


import com.example.pharma.domain.entities.compra.Compra;
import com.example.pharma.domain.entities.compra.CompraDetalle;
import com.example.pharma.domain.service.StockService;
import com.example.pharma.domain.service.producto.BodegaService;
import com.example.pharma.domain.service.producto.ProductoService;
import com.example.pharma.infrastructure.repository.compra.CompraDetalleRepository;
import com.example.pharma.shared.NotFoundException;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CompraDetalleService {

  private CompraDetalleRepository compraDetalleRepository;
  private StockService stockService;
  private ProductoService productoService;
  private BodegaService bodegaService;
  private CompraService compraService;
  public CompraDetalle create(CompraDetalle compraDetalle){

    CompraDetalle detalle = new CompraDetalle();

    detalle.setAmount(detalle.getAmount());

    var producto = productoService.findByName(compraDetalle.getProducto().getNombre());
    var bodega = bodegaService.getByName(compraDetalle.getBodegaDestino().getNombre());
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
    detalle.setProducto(producto);
    detalle.setBodegaDestino(bodega);
    detalle.setCompra(compra);

    var total = producto.getPrecioCosto() * detalle.getAmount();

    detalle.setTotal(total);

    return compraDetalleRepository.save(detalle);
  }

  public void comprarProducto(List<CompraDetalle> compraDetalleList){
    Compra invoiceUpdate = compraService.getById(compraDetalleList.get(0).getCompra().getCode());
    compraDetalleList.stream().forEach(invoiceDetail -> buildInvoice(invoiceDetail,invoiceUpdate));
    compraService.editCompra(invoiceUpdate);
  }


  private void buildInvoice(CompraDetalle compraDetalle, Compra compraUpdate) {

    create(compraDetalle);

    var product = productoService.getById(compraDetalle.getProducto().getId());

    var subtotalProduct = compraDetalle.getAmount() * product.getPrecioCosto();
    var iva = compraUpdate.getIva() / 100;

    var total = compraUpdate.getTotal() + subtotalProduct;
    var totalWithIva = compraUpdate.getTotalWithIva() + (subtotalProduct + (subtotalProduct * iva));
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




}

