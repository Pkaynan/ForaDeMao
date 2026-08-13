package com.pietro.forademao.repository;

import com.pietro.forademao.model.Fluxo_rodovia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Fluxo_rodoviaRepository extends JpaRepository<Fluxo_rodovia, Long> {
}
