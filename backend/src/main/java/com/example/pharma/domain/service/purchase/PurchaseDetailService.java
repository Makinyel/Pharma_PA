package com.example.pharma.domain.service.purchase;

import com.example.pharma.domain.entities.product.Product;
import com.example.pharma.domain.entities.product.Warehouse;
import com.example.pharma.domain.entities.purchase.Purchase;
import com.example.pharma.domain.entities.purchase.PurchaseDetail;
import com.example.pharma.domain.entities.stock.Stock;
import com.example.pharma.domain.service.product.ProductService;
import com.example.pharma.domain.service.product.WarehouseService;
import com.example.pharma.domain.service.stock.StockService;
import com.example.pharma.infrastructure.api.request.compra.PurchaseDetailRequest;
import com.example.pharma.infrastructure.repository.compra.PurchaseDetailRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PurchaseDetailService {

  private final PurchaseDetailRepository purchaseDetailRepository;
  private final ProductService productService;
  private final WarehouseService warehouseService;
  private final PurchaseService purchaseService;
  private final StockService stockService;

  public PurchaseDetail create(PurchaseDetail purchaseDetail) {

    Product product = productService.findById(purchaseDetail.getProduct().getId());
    Warehouse warehouse = warehouseService.findById(
        purchaseDetail.getDestinationWarehouse().getId());
    Purchase purchase = purchaseService.getById(purchaseDetail.getPurchase().getId());
    double total = product.getBuyingPrice() * purchaseDetail.getQuantity();

    purchaseDetail.setProduct(product);
    purchaseDetail.setDestinationWarehouse(warehouse);
    purchaseDetail.setPurchase(purchase);
    purchaseDetail.setTotal(total);

    Stock stock = Stock.builder()
        .productId(product.getId())
        .warehouseId(warehouse.getId())
        .build();

    stockService.create(stock, purchaseDetail.getQuantity());

    return purchaseDetailRepository.save(purchaseDetail);
  }

  public Purchase completePurchase(List<PurchaseDetail> purchaseDetailList) {
    Purchase invoiceUpdate = purchaseService.getById(
        purchaseDetailList.get(0).getPurchase().getId());
    purchaseDetailList.forEach(invoiceDetail -> buildPurchase(invoiceDetail, invoiceUpdate));

    return purchaseService.updatePurchase(invoiceUpdate);
  }


  private void buildPurchase(PurchaseDetail purchaseDetail, Purchase purchaseUpdate) {

    create(purchaseDetail);

    Product product = productService.findById(purchaseDetail.getProduct().getId());

    double subtotalProduct = purchaseDetail.getQuantity() * product.getBuyingPrice();

    double subtotal = purchaseUpdate.getSubtotal() + subtotalProduct;
    double total =
        purchaseUpdate.getTotal() + (subtotalProduct + (subtotalProduct * 0.19));
    double ivaInvoice = total - subtotal;

    purchaseUpdate.setSubtotal(subtotal);
    purchaseUpdate.setTotal(total);
    purchaseUpdate.setIva(ivaInvoice);

  }

  public List<PurchaseDetail> mapper(List<PurchaseDetailRequest> purchaseDetailRequest) {

    List<PurchaseDetail> purchaseDetailList = new ArrayList<>();

    for (PurchaseDetailRequest detail : purchaseDetailRequest) {
      purchaseDetailList.add(build(detail));
    }
    return purchaseDetailList;
  }

  public PurchaseDetail build(PurchaseDetailRequest request) {
    if (request == null) {
      return null;
    }
    PurchaseDetail purchaseDetail = new PurchaseDetail();

    Warehouse warehouse = warehouseService.findByName(request.getDestinationWarehouseName());
    Product product = productService.findByName(request.getProductName());
    Purchase purchase = purchaseService.getById(request.getPurchaseId());

    purchaseDetail.setQuantity(request.getQuantity());
    purchaseDetail.setDestinationWarehouse(warehouse);
    purchaseDetail.setProduct(product);
    purchaseDetail.setPurchase(purchase);

    return purchaseDetail;
  }
}

