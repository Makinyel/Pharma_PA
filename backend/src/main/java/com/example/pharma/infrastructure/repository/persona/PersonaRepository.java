package com.example.pharma.infrastructure.repository.persona;

import com.example.pharma.domain.entities.persona.Persona;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

  Optional<Persona> findByName(String name);

  Optional<Persona> findByDocument(String document);

  @Query(nativeQuery = true, value = "SELECT * FROM persona WHERE person_type = 1")
  List<Persona> findClients();

  @Query(nativeQuery = true, value = "SELECT * FROM persona WHERE person_type = 0")
  List<Persona> findProviders();
}