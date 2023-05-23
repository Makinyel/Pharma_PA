package com.example.pharma.domain.service.persona;

import com.example.pharma.domain.entities.keys.KeyPersona;
import com.example.pharma.domain.entities.persona.Persona;
import com.example.pharma.domain.entities.producto.Producto;
import com.example.pharma.infrastructure.repository.PersonaRepository;
import com.example.pharma.shared.NotFoundException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
@AllArgsConstructor
@Service
@Slf4j
public class PersonaService {

    private PersonaRepository personaRepository;

    public Persona savePersona(Persona persona) {
        return personaRepository.save(persona);
    }

    public Persona getPersonByID(String id, String tipoid) {
        var keyPerson = KeyPersona.builder().id(id).tipoId(tipoid).build();
        log.info("ESTA ES LA LLAVE " + keyPerson.toString());

        if (Objects.isNull(keyPerson)) {
            new NotFoundException("KEY NO ENCONTRADA");
        }

        Persona persona = personaRepository.getById(keyPerson);

        if (Objects.isNull(persona)) {
            new NotFoundException("Persona Con El ID {} No Esta Registrada " + persona.getId());
        }
        return persona;
    }

    public Persona findByName(String name) {
        Optional<Persona> persona = personaRepository.findByNombre(name);
        return persona
            .orElseThrow(() -> new NotFoundException
                ("Persona with name: " + name + " was not found."));
    }







    public List<Persona> getAllPerson() {
        return personaRepository.findAll();
    }

    public void editPerson(Persona persona) {
        var keyPerson = KeyPersona.builder().id(persona.getId()).tipoId(persona.getTipoId()).build();
        Persona person = personaRepository.getById(keyPerson);
        if (!Objects.isNull(person)) {
            person.setNombre(persona.getNombre());
            person.setTipoPersona(persona.getTipoPersona());
            person.setUbicacion(persona.getUbicacion());
            person.setTelefono(persona.getTelefono());
            person.setEmail(persona.getEmail());

            personaRepository.save(person);
        } else {
            new NotFoundException("Persona Con El ID {} No Esta Registrada " + person.getId());
        }
    }
    public void deletePersona(String id, String tipoid) {
        var keyPerson = KeyPersona.builder().id(id).tipoId(tipoid).build();
        personaRepository.deleteById(keyPerson);
    }
}