package com.pietro.forademao.model;

import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pietro.forademao.model.Enum.GravidadeEnum;
import com.pietro.forademao.model.Enum.TipoAcidenteEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "acidentes")
public class Acidente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private GravidadeEnum gravidadeEnum;

    @Enumerated(EnumType.STRING)
    private TipoAcidenteEnum tipo;

    private double latitude;
    private double longitude;
    private int fatais;
    private int feridos;
    private int ilesos;

    // TODO idclima
    
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAcidente", nullable = false)
    private Rodovia idRodovia;

    private int total_acidentes;
    private Instant data_hora;
}
