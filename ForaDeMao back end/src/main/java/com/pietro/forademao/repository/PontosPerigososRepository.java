package com.pietro.forademao.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pietro.forademao.model.Pontos_perigosos;

public interface PontosPerigososRepository extends JpaRepository<Pontos_perigosos, Long> {

    @Query("""
            SELECT p FROM Pontos_perigosos p
            WHERE p.latitude BETWEEN :minLat AND :maxLat
              AND p.longitude BETWEEN :minLon AND :maxLon
            """)
    List<Pontos_perigosos> findDentroDaArea(
            @Param("minLat") BigDecimal minLat,
            @Param("maxLat") BigDecimal maxLat,
            @Param("minLon") BigDecimal minLon,
            @Param("maxLon") BigDecimal maxLon);
}
