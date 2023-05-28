package com.example.pharma.infrastructure.repository.transfer;

import com.example.pharma.domain.entities.transfer.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {

  Transfer findTopByOrderByIdDesc();
}
