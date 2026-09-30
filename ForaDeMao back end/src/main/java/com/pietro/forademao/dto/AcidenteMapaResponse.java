package com.pietro.forademao.dto;

import java.math.BigDecimal;
import java.time.Instant;

import com.pietro.forademao.model.Acidente;

/**
 * Representação enxuta de um acidente para renderização no mapa (marcador).
 * Não inclui dados de clima nem a rodovia inteira — apenas o necessário
 * para desenhar o ponto e popular o popup.
 */
public record AcidenteMapaResponse(
        Long id,
        BigDecimal latitude,
        BigDecimal longitude,
        String tipo,
        String gravidade,
        int fatais,
        int feridos,
        int ilesos,
        Instant dataHora,
        Long idRodovia,
        String nomeRodovia) {

    public static AcidenteMapaResponse fromEntity(Acidente acidente) {
        return new AcidenteMapaResponse(
                acidente.getIdAcidente(),
                acidente.getLatitude(),
                acidente.getLongitude(),
                acidente.getTipoAcidenteEnum() != null ? acidente.getTipoAcidenteEnum().name() : null,
                acidente.getGravidadeEnum() != null ? acidente.getGravidadeEnum().name() : null,
                acidente.getFatais(),
                acidente.getFeridos(),
                acidente.getIlesos(),
                acidente.getData_hora(),
                acidente.getRodovia() != null ? acidente.getRodovia().getIdRodovia() : null,
                acidente.getRodovia() != null ? acidente.getRodovia().getNome() : null);
    }
}
