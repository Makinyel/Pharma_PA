package com.example.pharma.domain.service.persona;

import com.example.pharma.domain.entities.persona.Persona;
import com.example.pharma.infrastructure.repository.persona.PersonaRepository;
import com.example.pharma.shared.NotFoundException;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class PersonaService {

  private PersonaRepository personaRepository;

  public Persona savePersona(Persona persona) {
    return personaRepository.save(persona);
  }

  public Persona findPersonById(Long id) {
    return personaRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Third party id: " + id + " was not found."));
  }

  public Persona findByName(String name) {
    return personaRepository.findByName(name)
        .orElseThrow(() -> new NotFoundException("Persona with name: " + name + " was not found."));
  }


  public List<Persona> findAllPeople() {
    return personaRepository.findAll();
  }

  public void editPerson(Persona persona) {
    String document = persona.getDocument();
    Persona existingPerson = personaRepository
        .findByDocument(document)
        .orElseThrow(() -> new NotFoundException("Third party id: " + document + " does not exist"));

    existingPerson.setName(persona.getName());
    existingPerson.setDocumentType(persona.getDocumentType());
    existingPerson.setPersonType(persona.getPersonType());
    existingPerson.setLocation(persona.getLocation());
    existingPerson.setPhone(persona.getPhone());
    existingPerson.setEmail(persona.getEmail());

    personaRepository.save(existingPerson);
  }

  public void deletePerson(Long id) {
    personaRepository.deleteById(id);
  }
}