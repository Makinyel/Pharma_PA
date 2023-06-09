package com.example.pharma.domain.entities.transfer;

import com.example.pharma.domain.entities.product.Product;
import com.example.pharma.domain.entities.product.Warehouse;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Data
@Table(name = "detalle_traslado")
public class TransferDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private int quantity;
  @ManyToOne
  @JoinColumn(name = "product_id", referencedColumnName = "id")
  private Product product;
  @ManyToOne
  @JoinColumn(name = "source_warehouse_Id", referencedColumnName = "id")
  private Warehouse sourceWarehouseId;
  @ManyToOne
  @JoinColumn(name = "destination_warehouse_Id", referencedColumnName = "id")
  private Warehouse destinationWarehouseId;
  @ManyToOne
  @JoinColumn(name = "transfer_id", referencedColumnName = "id")
  private Transfer transferId;
}
