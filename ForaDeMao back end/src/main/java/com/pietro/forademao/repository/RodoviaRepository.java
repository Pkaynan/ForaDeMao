package com.pietro.forademao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pietro.forademao.model.Rodovia;

public interface RodoviaRepository extends JpaRepository<Rodovia, Long> {

    // EntityGraph evita N+1 queries: busca as rodovias já com o traçado
    // (pontosTracado) carregado, em vez de uma query por rodovia.
    @EntityGraph(attributePaths = "pontosTracado")
    List<Rodovia> findAll();
}
