package com.pietro.forademao.repository;

import com.pietro.forademao.model.Conexao_rodovia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Conexao_rodoviaRepository extends JpaRepository<Conexao_rodovia, Long> {
}
