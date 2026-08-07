package com.pietro.forademao.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pietro.forademao.model.Enum.Nivel_risco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pontos_perigosos")
public class Pontos_perigosos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpontos_perigosos")
    private Long idpontos_perigosos;

    private BigDecimal latitude;
    private BigDecimal longitude;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_risco")
    private Nivel_risco nivel_riscoENUM;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idrodovia", nullable = false)
    private Rodovia idRodovia;
}
