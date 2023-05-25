package com.example.pharma.infrastructure.repository.transfer;

import com.example.pharma.domain.entities.transfer.TransferDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferDetailRepository extends JpaRepository<TransferDetail, Long> {

}
