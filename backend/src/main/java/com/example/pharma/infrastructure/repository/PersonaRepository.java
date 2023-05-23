package com.example.pharma.infrastructure.repository;

import com.example.pharma.domain.entities.keys.KeyPersona;
import com.example.pharma.domain.entities.persona.Persona;
import com.example.pharma.domain.entities.producto.Bodega;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, KeyPersona> {
  Optional<Persona> findByNombre(String name);

}
