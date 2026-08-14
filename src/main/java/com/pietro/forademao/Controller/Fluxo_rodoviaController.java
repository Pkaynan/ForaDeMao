package com.pietro.forademao.Controller;

import com.pietro.forademao.Service.Fluxo_rodoviaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fluxo")
public class Fluxo_rodoviaController {

    private final Fluxo_rodoviaService fluxoRodoviaService;

    public Fluxo_rodoviaController(Fluxo_rodoviaService fluxoRodoviaService){
        this.fluxoRodoviaService = fluxoRodoviaService;
    }
}
