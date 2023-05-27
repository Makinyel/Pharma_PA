package com.example.pharma.domain.service.transfer;

import com.example.pharma.domain.entities.product.Product;
import com.example.pharma.domain.entities.product.Warehouse;
import com.example.pharma.domain.entities.stock.Stock;
import com.example.pharma.domain.entities.transfer.TransferDetail;
import com.example.pharma.domain.service.product.ProductService;
import com.example.pharma.domain.service.product.WarehouseService;
import com.example.pharma.domain.service.stock.StockService;
import com.example.pharma.infrastructure.api.request.transfer.TransferDetailRequest;
import com.example.pharma.infrastructure.repository.transfer.TransferDetailRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TransferDetailService {

  private final TransferDetailRepository transferDetailRepository;
  private final WarehouseService warehouseService;
  private final ProductService productService;
  private final StockService stockService;

  public TransferDetail create(TransferDetail transferDetail) {

    Product product = transferDetail.getProduct();

    Warehouse origenWarehouse = warehouseService.findById(
        transferDetail.getSourceWarehouseId().getId());

    Warehouse destinationWarehouse = warehouseService.findById(
        transferDetail.getDestinationWarehouseId().getId());

    transferDetail.setQuantity(transferDetail.getQuantity());
    transferDetail.setProduct(product);
    transferDetail.setSourceWarehouseId(origenWarehouse);
    transferDetail.setDestinationWarehouseId(destinationWarehouse);

    Stock stockOrigen = stockService.getByIdProductIdBodega(product.getId(),
        origenWarehouse.getId());

    Stock stockDestination = Stock.builder().
        productId(product.getId()).
        warehouseId(destinationWarehouse.getId()).build();

    //Sumamos Al wareHouse Destino
    stockService.create(stockDestination, transferDetail.getQuantity());

    //Sumamos A la wareHouse Origen
    stockService.restarStockMovimientos(stockOrigen, transferDetail.getQuantity());

    return transferDetailRepository.save(transferDetail);
  }

  public void completeTransfer(List<TransferDetail> transferDetailList) {
    transferDetailList.forEach(this::create);
  }

  public List<TransferDetail> mapper(List<TransferDetailRequest> transferDetailRequestList) {

    List<TransferDetail> transferDetails = new ArrayList<>();

    for (TransferDetailRequest detail : transferDetailRequestList) {
      transferDetails.add(build(detail));
    }
    return transferDetails;
  }

  public TransferDetail build(TransferDetailRequest request) {
    if (request == null) {
      return null;
    }
    TransferDetail transferDetail = new TransferDetail();

    Warehouse origenWarehouse = warehouseService.findByName(
        request.getOrigenwarehouseName());

    Warehouse destinationWarehouse = warehouseService.findByName(
        request.getDestinationWarehouseName());

    Product product = productService.findByName(request.getProductName());

    transferDetail.setQuantity(request.getQuantity());
    transferDetail.setSourceWarehouseId(origenWarehouse);
    transferDetail.setDestinationWarehouseId(destinationWarehouse);
    transferDetail.setProduct(product);

    return transferDetail;
  }
}
