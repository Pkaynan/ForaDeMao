package com.pietro.forademao.Service;

import com.pietro.forademao.repository.Fluxo_rodoviaRepository;
import org.springframework.stereotype.Service;

@Service
public class Fluxo_rodoviaService {

    private final Fluxo_rodoviaRepository fluxoRodoviaRepository;

    public Fluxo_rodoviaService (Fluxo_rodoviaRepository fluxoRodoviaRepository){
        this.fluxoRodoviaRepository = fluxoRodoviaRepository;
    }
}
