package com.pietro.forademao.Service;

import com.pietro.forademao.repository.RodoviaRespository;
import org.springframework.stereotype.Service;

@Service
public class RodoviaService {

    private final RodoviaRespository rodoviaRespository;

    public RodoviaService(RodoviaRespository rodoviaRespository){
        this.rodoviaRespository = rodoviaRespository;
    }
}
