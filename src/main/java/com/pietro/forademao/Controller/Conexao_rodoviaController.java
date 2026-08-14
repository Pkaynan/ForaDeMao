package com.pietro.forademao.Controller;

import com.pietro.forademao.Service.Conexao_rodoviaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conexao")
public class Conexao_rodoviaController {

    private final Conexao_rodoviaService conexaoRodoviaService;

    public Conexao_rodoviaController(Conexao_rodoviaService conexaoRodoviaService){
        this.conexaoRodoviaService = conexaoRodoviaService;
    }
}
