package com.example.pharma.infrastructure.api.controller.transfer;

import com.example.pharma.domain.entities.transfer.Transfer;
import com.example.pharma.domain.service.transfer.TransferService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/transfer")
public class TransferController {

  private final TransferService transferService;

  @PostMapping
  public ResponseEntity<Transfer> create(
      @RequestHeader("user-email") String email) {
    return ResponseEntity.ok(transferService.initTransfer(email));
  }
}
