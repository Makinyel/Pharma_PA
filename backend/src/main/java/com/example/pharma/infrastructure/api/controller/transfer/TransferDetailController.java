package com.example.pharma.infrastructure.api.controller.transfer;

import com.example.pharma.domain.service.transfer.TransferDetailService;
import com.example.pharma.infrastructure.api.request.transfer.TransferDetailRequest;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/transfer-details")
public class TransferDetailController {

  private final TransferDetailService transferDetailService;

  @PostMapping
  public void create(
      @RequestBody List<TransferDetailRequest> transferDetailRequestList) {
    transferDetailService.completeTransfer(transferDetailService.mapper(transferDetailRequestList));
  }
}
