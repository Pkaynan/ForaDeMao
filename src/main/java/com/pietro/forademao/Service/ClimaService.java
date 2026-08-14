package com.pietro.forademao.Service;

import com.pietro.forademao.repository.ClimaRepository;
import org.springframework.stereotype.Service;

@Service
public class ClimaService {

    private final ClimaRepository climaRepository;

    public ClimaService (ClimaRepository climaRepository){
        this.climaRepository = climaRepository;
    }
}
