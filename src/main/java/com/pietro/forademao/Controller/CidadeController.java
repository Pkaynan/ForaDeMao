package com.pietro.forademao.Controller;


import com.pietro.forademao.Service.CidadeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cidade")
public class CidadeController {

    private final CidadeService cidadeService;

    public CidadeController(CidadeService cidadeService){
        this.cidadeService = cidadeService;
    }
}
