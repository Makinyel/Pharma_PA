package com.example.pharma.infrastructure.repository;

import com.example.pharma.domain.entities.persona.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

  Optional<Persona> findByName(String name);

  Optional<Persona> findByDocument(String document);
}