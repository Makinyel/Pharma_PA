package com.example.pharma.domain.service.transfer;

import com.example.pharma.domain.entities.transfer.Transfer;
import com.example.pharma.domain.entities.user.User;
import com.example.pharma.domain.service.usuario.UsuarioService;
import com.example.pharma.infrastructure.repository.transfer.TransferRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TransferService {

  private final TransferRepository transferRepository;
  private final UsuarioService usuarioService;

  public Transfer initTransfer(String email) {

    Transfer transfer = new Transfer();

    User user = usuarioService.findByEmail(email);

    transfer.setDate(LocalDate.now());
    transfer.setUser(user);

    return transferRepository.save(transfer);
  }

  public List<Transfer> findAll() {
    return transferRepository.findAll();
  }

  public Transfer findLast() {
    return transferRepository.findTopByOrderByIdDesc();
  }
}
