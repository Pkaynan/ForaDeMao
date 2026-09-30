package com.pietro.forademao.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pietro.forademao.model.Acidente;

public interface AcidenteRepository extends JpaRepository<Acidente, Long> {

    // Filtra por área visível do mapa (bounding box), evitando trazer a base
    // inteira para o navegador quando o usuário está com zoom em uma cidade.
    @Query("""
            SELECT a FROM Acidente a
            WHERE a.latitude BETWEEN :minLat AND :maxLat
              AND a.longitude BETWEEN :minLon AND :maxLon
            """)
    List<Acidente> findDentroDaArea(
            @Param("minLat") BigDecimal minLat,
            @Param("maxLat") BigDecimal maxLat,
            @Param("minLon") BigDecimal minLon,
            @Param("maxLon") BigDecimal maxLon);

    List<Acidente> findByRodovia_IdRodovia(Long idRodovia);
}
