package com.pietro.forademao.Service;

import com.pietro.forademao.repository.Pontos_perigososRepository;
import org.springframework.stereotype.Service;

@Service
public class Pontos_perigososService {

    private final Pontos_perigososRepository pontosPerigososRepository;

    public Pontos_perigososService(Pontos_perigososRepository pontosPerigososRepository){
        this.pontosPerigososRepository = pontosPerigososRepository;
    }
}