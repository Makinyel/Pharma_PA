package com.example.pharma.infrastructure.api.controller.persona;

import com.example.pharma.domain.entities.persona.Persona;
import com.example.pharma.domain.service.persona.PersonaService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/third-party")
public class PersonaController {

  private PersonaService personaService;

  @PostMapping
  public ResponseEntity<Persona> savePerson(@RequestBody Persona persona) {
    return ResponseEntity.ok(personaService.savePersona(persona));
  }

  @GetMapping("{id}")
  public ResponseEntity<Persona> findPersonById(@PathVariable Long id) {
    return ResponseEntity.ok(personaService.findPersonById(id));
  }

  @GetMapping("clients")
  public ResponseEntity<List<Persona>> findClients(){
    return ResponseEntity.ok(personaService.findClients());
  }

  @GetMapping("providers")
  public ResponseEntity<List<Persona>> findProviders(){
    return ResponseEntity.ok(personaService.findProviders());
  }

  @GetMapping
  public ResponseEntity<List<Persona>> findAllPeople() {
    return ResponseEntity.ok(personaService.findAllPeople());
  }

  @PutMapping
  public void editPerson(@RequestBody Persona persona) {
    personaService.editPerson(persona);
  }

  @DeleteMapping("{id}")
  public void deletePerson(@PathVariable Long id) {
    personaService.deletePerson(id);
  }
}
