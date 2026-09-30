package com.pietro.forademao.Controller;

import java.math.BigDecimal;
import java.util.List;

import com.pietro.forademao.Service.MapaService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pietro.forademao.dto.AcidenteMapaResponse;
import com.pietro.forademao.dto.PontoPerigosoMapaResponse;
import com.pietro.forademao.dto.RodoviaMapaResponse;

@RestController
@RequestMapping("/api/mapa")
@CrossOrigin(origins = "http://localhost:4200") // origem padrão do Angular em dev
public class MapaController {

    private final MapaService mapaService;

    public MapaController(MapaService mapaService) {
        this.mapaService = mapaService;
    }

    // GET /api/mapa/acidentes
    // GET /api/mapa/acidentes?minLat=-24&maxLat=-23&minLon=-47&maxLon=-46
    @GetMapping("/acidentes")
    public List<AcidenteMapaResponse> acidentes(
            @RequestParam(required = false) BigDecimal minLat,
            @RequestParam(required = false) BigDecimal maxLat,
            @RequestParam(required = false) BigDecimal minLon,
            @RequestParam(required = false) BigDecimal maxLon) {
        return mapaService.buscarAcidentes(minLat, maxLat, minLon, maxLon);
    }

    // GET /api/mapa/rodovias
    @GetMapping("/rodovias")
    public List<RodoviaMapaResponse> rodovias() {
        return mapaService.buscarRodovias();
    }

    // GET /api/mapa/pontos-perigosos
    @GetMapping("/pontos-perigosos")
    public List<PontoPerigosoMapaResponse> pontosPerigosos(
            @RequestParam(required = false) BigDecimal minLat,
            @RequestParam(required = false) BigDecimal maxLat,
            @RequestParam(required = false) BigDecimal minLon,
            @RequestParam(required = false) BigDecimal maxLon) {
        return mapaService.buscarPontosPerigosos(minLat, maxLat, minLon, maxLon);
    }
}
