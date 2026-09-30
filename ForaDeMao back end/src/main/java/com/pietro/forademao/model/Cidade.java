package com.pietro.forademao.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cidade")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcidade")
    private Long idCidade;

    private String nome;
    private String estado;

    @JsonManagedReference
    @OneToMany(mappedBy = "cidade_origem", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Conexao_rodovia> conexaoOrigem = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "cidade_destino", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Conexao_rodovia> conexaoDestino = new ArrayList<>();
}
