package com.pietro.forademao.dto;

import java.util.Comparator;
import java.util.List;

import com.pietro.forademao.model.Rodovia;
import com.pietro.forademao.model.RodoviaPonto;

public record RodoviaMapaResponse(
        Long id,
        String nome,
        String tipo,
        String estado,
        List<CoordenadaResponse> tracado,
        int km) {

    public static RodoviaMapaResponse fromEntity(Rodovia rodovia) {
        List<CoordenadaResponse> tracado = rodovia.getPontosTracado().stream()
                .sorted(Comparator.comparingInt(RodoviaPonto::getOrdem))
                .map(p -> new CoordenadaResponse(p.getLatitude(), p.getLongitude()))
                .toList();

        return new RodoviaMapaResponse(
                rodovia.getIdRodovia(),
                rodovia.getNome(),
                rodovia.getTipo(),
                rodovia.getEstado(),
                tracado,
                rodovia.getKm());
    }
}
