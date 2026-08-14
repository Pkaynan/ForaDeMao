package com.pietro.forademao.Service;

import com.pietro.forademao.repository.CidadeRepository;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

    private final CidadeRepository cidadeRepository;

    public CidadeService (CidadeRepository cidadeRepository){
        this.cidadeRepository = cidadeRepository;
    }


}
