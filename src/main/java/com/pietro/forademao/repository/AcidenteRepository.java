package com.pietro.forademao.repository;

import com.pietro.forademao.model.Acidente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AcidenteRepository extends JpaRepository<Acidente, UUID> {
}
