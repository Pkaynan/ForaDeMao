package com.pietro.forademao.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
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
    @Column(name = "idrodovia")
    private Long idRodovia;

    private int km;
    private String nome;
    private String tipo;
    private String estado;

    @JsonManagedReference
    @OneToMany(mappedBy = "rodovia", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true) // TODO VERIFICAR O CASCADE
    private List<Acidente> idAcidente = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "rodovia", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true) // TODO VERIFICAR O CASCADE
    private List<Pontos_perigosos> idPontosPerigosos = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "rodovia", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)//TODO VERIFICAR O CASCADE
    private List<Conexao_rodovia> conexaoRodovias = new ArrayList<>();
}
