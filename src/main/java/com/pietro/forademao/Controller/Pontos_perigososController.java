package com.pietro.forademao.Controller;

import com.pietro.forademao.Service.Pontos_perigososService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pontos")
public class Pontos_perigososController {

    private Pontos_perigososService pontosPerigososService;

    public Pontos_perigososController (Pontos_perigososService pontosPerigososService){
        this.pontosPerigososService = pontosPerigososService;
    }
}
