package com.example.pharma.infrastructure.repository;

import com.example.pharma.domain.entities.traslado.Traslado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrasladoRepository extends JpaRepository<Traslado,Long> {

}
