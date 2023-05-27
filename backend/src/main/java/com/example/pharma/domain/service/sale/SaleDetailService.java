package com.example.pharma.domain.service.sale;

import com.example.pharma.domain.entities.product.Product;
import com.example.pharma.domain.entities.product.Warehouse;
import com.example.pharma.domain.entities.sale.Sale;
import com.example.pharma.domain.entities.sale.SaleDetail;
import com.example.pharma.domain.entities.stock.Stock;
import com.example.pharma.domain.service.product.ProductService;
import com.example.pharma.domain.service.product.WarehouseService;
import com.example.pharma.domain.service.stock.StockService;
import com.example.pharma.infrastructure.api.request.sale.SaleDetailRequest;
import com.example.pharma.infrastructure.repository.sale.SaleDetailRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SaleDetailService {

  private SaleDetailRepository saleDetailRepository;
  private ProductService productService;
  private WarehouseService warehouseService;
  private StockService stockService;
  private SaleService saleService;

  public SaleDetail create(SaleDetail saleDetail) {

    Product product = productService.findById(saleDetail.getProduct().getId());
    Warehouse warehouse = warehouseService.findByName(
        saleDetail.getSourceWarehouse().getName());
    Sale sale = saleService.getById(saleDetail.getSale().getId());

    double total = product.getSellingPrice() * saleDetail.getQuantity();

    saleDetail.setProduct(product);
    saleDetail.setSourceWarehouse(warehouse);
    saleDetail.setSale(sale);
    saleDetail.setTotal(total);

    Stock stock = stockService.getByIdProductIdBodega(product.getId(), warehouse.getId());

    stockService.restarStockMovimientos(stock, saleDetail.getQuantity());

    return saleDetailRepository.save(saleDetail);

  }

  public void completeSale(List<SaleDetail> saleDetailList) {
    Sale saleUpdate = saleService.getById(
        saleDetailList.get(0).getSale().getId());
    saleDetailList.forEach(saleDetail -> builSale(saleDetail, saleUpdate));
    saleService.updateSale(saleUpdate);
  }

  private void builSale(SaleDetail saleDetail, Sale saleUpdate) {

    create(saleDetail);

    Product product = productService.findById(saleDetail.getProduct().getId());

    double subtotalProduct = saleDetail.getQuantity() * product.getBuyingPrice();

    double subtotal = saleUpdate.getSubtotal() + subtotalProduct;
    double total =
        saleUpdate.getTotal() + (subtotalProduct + (subtotalProduct * 0.19));
    double ivaInvoice = total - subtotal;

    saleUpdate.setSubtotal(subtotal);
    saleUpdate.setTotal(total);
    saleUpdate.setIva(ivaInvoice);

  }

  public List<SaleDetail> mapper(List<SaleDetailRequest> saleDetailRequests) {

    List<SaleDetail> saleDetaillist = new ArrayList<>();

    for (SaleDetailRequest detail : saleDetailRequests) {
      saleDetaillist.add(build(detail));
    }
    return saleDetaillist;
  }

  public SaleDetail build(SaleDetailRequest request) {
    if (request == null) {
      return null;
    }
    SaleDetail saleDetail = new SaleDetail();

    Warehouse warehouse = warehouseService.findByName(request.getOrigenwarehouseName());
    Product product = productService.findByName(request.getProductName());
    Sale sale = saleService.getById(request.getSaleId());

    saleDetail.setQuantity(request.getQuantity());
    saleDetail.setSourceWarehouse(warehouse);
    saleDetail.setProduct(product);
    saleDetail.setSale(sale);

    return saleDetail;
  }


}
