package com.example.pharma.infrastructure.api.controller;

import com.example.pharma.domain.entities.persona.Persona;
import com.example.pharma.domain.service.PersonaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/personas")
public class PersonaController {
    private PersonaService personaService;

    @PostMapping
    public ResponseEntity<Persona> savePersona(@RequestBody Persona persona) {
        return new ResponseEntity<>(personaService.savePersona(persona),HttpStatus.CREATED);
    }

    @GetMapping("/{id}/{tipoid}")
    public ResponseEntity<Persona> getPersonabyid(@PathVariable String id, @PathVariable String tipoid) {
        return ResponseEntity.ok(personaService.getPersonByID(id,tipoid));
    }
    @GetMapping
    public ResponseEntity<List<Persona>> getPersonAll(){
        return ResponseEntity.ok(personaService.getAllPerson());
    }
    @PutMapping
    public void editPerson(@RequestBody Persona persona){
        personaService.editPerson(persona);
    }

    @DeleteMapping("/{id}/{tipoid}")
    public void deletePerson(@PathVariable String id,@PathVariable String tipoid) {
        personaService.deletePersona(id,tipoid);
    }
}
