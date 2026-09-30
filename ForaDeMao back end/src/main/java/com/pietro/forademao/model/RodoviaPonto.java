package com.pietro.forademao.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Um ponto (vértice) do traçado geográfico de uma rodovia.
 * A lista ordenada de pontos de uma Rodovia forma a linha (LineString)
 * que é desenhada no mapa (Leaflet Polyline / GeoJSON LineString).
 */
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rodovia_ponto")
public class RodoviaPonto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrodovia_ponto")
    private Long idRodoviaPonto;

    private BigDecimal latitude;
    private BigDecimal longitude;

    /** Define a ordem do ponto dentro do traçado da rodovia (0, 1, 2, ...). */
    @Column(name = "ordem")
    private int ordem;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idrodovia", nullable = false)
    private Rodovia rodovia;
}
