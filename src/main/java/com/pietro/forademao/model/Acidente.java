package com.pietro.forademao.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pietro.forademao.model.Enum.GravidadeEnum;
import com.pietro.forademao.model.Enum.TipoAcidenteEnum;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "acidente")
public class Acidente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idacidente")
    private Long idAcidente;

    @Enumerated(EnumType.STRING)
    @Column(name = "gravidade")
    private GravidadeEnum gravidadeEnum;

    @Enumerated(EnumType.STRING)
    private TipoAcidenteEnum tipo;

    private BigDecimal latitude;
    private BigDecimal longitude;
    private int fatais;
    private int feridos;
    private int ilesos;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idrodovia", nullable = false)
    private Rodovia rodovia;

    private int total_acidentes;
    private Instant data_hora;

    @JsonManagedReference
    @OneToMany(mappedBy = "acidente", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true) // TODO VERIFICAR O CASCADE
    private List<Clima> climaList = new ArrayList<>();
}
