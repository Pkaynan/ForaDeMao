package com.pietro.forademao.Controller;

import com.pietro.forademao.Service.AcidenteService;
import com.pietro.forademao.model.Acidente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acidente")
public class AcidenteController {

    private final AcidenteService acidenteService;

    public AcidenteController(AcidenteService acidenteService){
        this.acidenteService = acidenteService;
    }

    /*@PostMapping
    public ResponseEntity<Acidente> insert(@RequestBody Acidente acidente){
        return ResponseEntity.ok();
    }*/

    @GetMapping
    public ResponseEntity<List<Acidente>> getall(){
        List<Acidente> acidentes = this.acidenteService.getall();
        return ResponseEntity.ok().body(acidentes);
    }
}
