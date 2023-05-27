package com.example.pharma.domain.service.sale;

import com.example.pharma.domain.entities.persona.Persona;
import com.example.pharma.domain.entities.sale.Sale;
import com.example.pharma.domain.entities.user.User;
import com.example.pharma.domain.service.persona.PersonaService;
import com.example.pharma.domain.service.usuario.UsuarioService;
import com.example.pharma.infrastructure.api.request.sale.SaleRequest;
import com.example.pharma.infrastructure.repository.sale.SaleRepository;
import com.example.pharma.shared.NotFoundException;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SaleService {

  private final SaleRepository saleRepository;
  private final UsuarioService usuarioService;
  private final PersonaService personaService;


  public Sale initSale(SaleRequest saleRequest, String email) {

    Sale sale = new Sale();

    sale.setIva(0.0);
    sale.setSubtotal(0.0);
    sale.setTotal(0.0);
    sale.setDate(LocalDate.now());

    User user = usuarioService.findByEmail(email);
    Persona person = personaService.findByName(saleRequest.getClientName());

    sale.setUser(user);
    sale.setClient(person);

    return saleRepository.save(sale);

  }

  public Sale getById(Long id) {
    return saleRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Sale not found"));
  }

  @Transactional
  public Sale updateSale(Sale saleDetails) {
    saleRepository.findById(saleDetails.getId())
        .orElseThrow(() -> new NotFoundException("Sale not found"));
    return saleRepository.save(saleDetails);
  }
}
