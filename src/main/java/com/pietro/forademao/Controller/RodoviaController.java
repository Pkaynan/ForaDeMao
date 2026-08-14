package com.pietro.forademao.Controller;

import com.pietro.forademao.Service.RodoviaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clima")
public class RodoviaController {

    private final RodoviaService rodoviaService;

    public RodoviaController(RodoviaService rodoviaService) {
        this.rodoviaService = rodoviaService;
    }
}
