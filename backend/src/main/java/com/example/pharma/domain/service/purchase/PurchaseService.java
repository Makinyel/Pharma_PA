package com.example.pharma.domain.service.purchase;

import com.example.pharma.domain.entities.persona.Persona;
import com.example.pharma.domain.entities.purchase.Purchase;
import com.example.pharma.domain.entities.user.User;
import com.example.pharma.domain.service.persona.PersonaService;
import com.example.pharma.domain.service.usuario.UsuarioService;
import com.example.pharma.infrastructure.api.request.compra.PurchaseRequest;
import com.example.pharma.infrastructure.repository.compra.PurchaseRepository;
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
public class PurchaseService {

  private PurchaseRepository purchaseRepository;
  private PersonaService personaService;
  private UsuarioService usuarioService;

  public Purchase initPurchase(PurchaseRequest purchaseRequest, String email) {

    Purchase purchase = new Purchase();

    purchase.setDate(LocalDate.now());
    purchase.setInvoiceCode(purchaseRequest.getInvoiceCode());
    purchase.setSubtotal(0.0);
    purchase.setTotal(0.0);
    purchase.setIva(0.0);

    User user = usuarioService.findByEmail(email);
    Persona person = personaService.findByName(purchaseRequest.getProviderName());

    purchase.setUser(user);
    purchase.setProvider(person);

    return purchaseRepository.save(purchase);
  }

  public Purchase getById(Long id) {
    return purchaseRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Purchase not found"));
  }

  @Transactional
  public Purchase updatePurchase(Purchase purchase) {
    purchaseRepository.findById(purchase.getId())
        .orElseThrow(() -> new NotFoundException("Purchase not found"));
    return purchaseRepository.save(purchase);
  }

  public List<Purchase> findAll() {
    return purchaseRepository.findAll();
  }

  public Purchase findLast() {
    return purchaseRepository.findTopByOrderByIdDesc();
  }
}
