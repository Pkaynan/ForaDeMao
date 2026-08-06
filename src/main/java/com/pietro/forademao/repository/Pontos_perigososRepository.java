package com.pietro.forademao.repository;

import com.pietro.forademao.model.Pontos_perigosos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Pontos_perigososRepository extends JpaRepository<Pontos_perigosos, Long> {
}
