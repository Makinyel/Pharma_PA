package com.example.pharma.infrastructure.repository;

import com.example.pharma.domain.entities.keys.KeyPersona;
import com.example.pharma.domain.entities.persona.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, KeyPersona> {
}
