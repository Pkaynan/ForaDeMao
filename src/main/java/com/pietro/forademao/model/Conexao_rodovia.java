package com.pietro.forademao.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "conexao_rodovia")
public class Conexao_rodovia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idconexao")
    private Long idconexao;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cidade_destino", nullable = false)
    private Cidade cidade_destino;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cidade_origem", nullable = false)
    private Cidade cidade_origem;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idrodovia", nullable = false)
    private Rodovia idrodovia;

    @Column(name = "distancia_km")
    private int distancia;
}
