package com.pietro.forademao.Service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pietro.forademao.dto.AcidenteMapaResponse;
import com.pietro.forademao.dto.PontoPerigosoMapaResponse;
import com.pietro.forademao.dto.RodoviaMapaResponse;
import com.pietro.forademao.repository.AcidenteRepository;
import com.pietro.forademao.repository.PontosPerigososRepository;
import com.pietro.forademao.repository.RodoviaRepository;

@Service
public class MapaService {

    private final AcidenteRepository acidenteRepository;
    private final RodoviaRepository rodoviaRepository;
    private final PontosPerigososRepository pontosPerigososRepository;

    public MapaService(AcidenteRepository acidenteRepository, RodoviaRepository rodoviaRepository, PontosPerigososRepository pontosPerigososRepository) {
        this.acidenteRepository = acidenteRepository;
        this.rodoviaRepository = rodoviaRepository;
        this.pontosPerigososRepository = pontosPerigososRepository;
    }

    public List<AcidenteMapaResponse> buscarAcidentes(
            BigDecimal minLat, BigDecimal maxLat, BigDecimal minLon, BigDecimal maxLon) {

        var acidentes = (minLat == null)
                ? acidenteRepository.findAll()
                : acidenteRepository.findDentroDaArea(minLat, maxLat, minLon, maxLon);

        return acidentes.stream().map(AcidenteMapaResponse::fromEntity).toList();
    }

    public List<RodoviaMapaResponse> buscarRodovias() {
        return rodoviaRepository.findAll().stream()
                .map(RodoviaMapaResponse::fromEntity)
                .toList();
    }

    public List<PontoPerigosoMapaResponse> buscarPontosPerigosos(
            BigDecimal minLat, BigDecimal maxLat, BigDecimal minLon, BigDecimal maxLon) {

        var pontos = (minLat == null)
                ? pontosPerigososRepository.findAll()
                : pontosPerigososRepository.findDentroDaArea(minLat, maxLat, minLon, maxLon);

        return pontos.stream().map(PontoPerigosoMapaResponse::fromEntity).toList();
    }
}
