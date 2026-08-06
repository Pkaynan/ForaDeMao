package com.pietro.forademao.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pietro.forademao.model.Enum.Nivel_risco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pontos_perigosos")
public class Pontos_perigosos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idpontos_perigosos;

    private float latitude;
    private float longitude;
    private Nivel_risco nivel_risco;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRodovia", nullable = false)
    private Rodovia idRodovia;
}
