package com.pietro.forademao.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rodovia")
public class Rodovia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRodovia;

    private double km;
    private String nome;
    private String tipo;
    private String estado;

    @JsonManagedReference
    @OneToMany(mappedBy = "idRodovia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Acidente> idAcidente = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "idRodovia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pontos_perigosos> idPontosPerigosos = new ArrayList<>();
}
