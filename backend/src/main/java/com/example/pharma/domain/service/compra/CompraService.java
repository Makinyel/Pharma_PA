package com.example.pharma.domain.service.compra;

import com.example.pharma.domain.entities.compra.Compra;
import com.example.pharma.domain.entities.persona.Persona;
import com.example.pharma.domain.entities.user.User;
import com.example.pharma.domain.service.persona.PersonaService;
import com.example.pharma.domain.service.usuario.UsuarioService;
import com.example.pharma.infrastructure.api.request.compra.CompraRequest;
import com.example.pharma.infrastructure.repository.compra.CompraRepository;
import com.example.pharma.shared.NotFoundException;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class CompraService {

  private CompraRepository compraRepository;
  private PersonaService personaService;
  private UsuarioService usuarioService;

  public Compra save(CompraRequest compraRequest, String email) {

    Compra compra = new Compra();

    compra.setDate(LocalDate.now());
    compra.setCodeInvoice(compraRequest.getInvoiceCode());
    compra.setTotal(0);
    compra.setTotalWithIva(0);

    User user = usuarioService.findByEmail(email);
    Persona person = personaService.findByName(compraRequest.getProviderName());

    compra.setUser(user);
    compra.setProvider(person);

    return compraRepository.save(compra);
  }

  public List<Compra> getAll() {
    return compraRepository.findAll();
  }

  public Compra getById(Long id) {
    return compraRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Compra no encontrada"));
  }


  @Transactional
  public void editCompra(Compra compra) {
    compraRepository.editCompra(
        compra.getCode(),
        compra.getTotal(),
        compra.getTotalWithIva(),
        compra.getIva());
  }
}
