package com.pietro.forademao.repository;

import com.pietro.forademao.model.Rodovia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RodoviaRespository extends JpaRepository<Rodovia, UUID> {
}
