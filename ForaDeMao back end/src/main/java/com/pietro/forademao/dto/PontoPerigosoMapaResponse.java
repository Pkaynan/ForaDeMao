package com.pietro.forademao.dto;

import java.math.BigDecimal;

import com.pietro.forademao.model.Pontos_perigosos;

public record PontoPerigosoMapaResponse(
        Long id,
        BigDecimal latitude,
        BigDecimal longitude,
        String nivelRisco,
        Long idRodovia,
        String nomeRodovia) {

    public static PontoPerigosoMapaResponse fromEntity(Pontos_perigosos ponto) {
        return new PontoPerigosoMapaResponse(
                ponto.getIdpontos_perigosos(),
                ponto.getLatitude(),
                ponto.getLongitude(),
                ponto.getNivel_riscoEnum() != null ? ponto.getNivel_riscoEnum().name() : null,
                ponto.getRodovia() != null ? ponto.getRodovia().getIdRodovia() : null,
                ponto.getRodovia() != null ? ponto.getRodovia().getNome() : null);
    }
}
