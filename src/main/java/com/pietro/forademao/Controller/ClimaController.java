package com.pietro.forademao.Controller;

import com.pietro.forademao.Service.ClimaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clima")
public class ClimaController {

    private final ClimaService climaService;

    public ClimaController (ClimaService climaService){
        this.climaService = climaService;
    }


}
