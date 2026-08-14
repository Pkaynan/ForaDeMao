package com.pietro.forademao.Service;

import com.pietro.forademao.model.Acidente;
import com.pietro.forademao.repository.AcidenteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcidenteService {

    private final AcidenteRepository acidenteRepository;

    public AcidenteService(AcidenteRepository acidenteRepository){
        this.acidenteRepository = acidenteRepository;
    }

    public List<Acidente> getall() {
        return this.acidenteRepository.findAll();
    }
}
