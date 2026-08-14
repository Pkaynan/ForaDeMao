package com.pietro.forademao.Service;

import com.pietro.forademao.repository.Conexao_rodoviaRepository;
import org.springframework.stereotype.Service;

@Service
public class Conexao_rodoviaService {

    private final Conexao_rodoviaRepository conexaoRodoviaRepository;

    public Conexao_rodoviaService (Conexao_rodoviaRepository conexaoRodoviaRepository){
        this.conexaoRodoviaRepository = conexaoRodoviaRepository;
    }
}
