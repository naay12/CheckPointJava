package com.example.clinicadentista.service;

import com.example.clinicadentista.model.DentistaModel;
import com.example.clinicadentista.repository.DentistaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentistaService {

    private final DentistaRepository dentistaRepository;


    public DentistaService(DentistaRepository dentistaRepository) {

        this.dentistaRepository = dentistaRepository;
    }

    public DentistaModel salvar(DentistaModel dentistaModel){

        return dentistaRepository.save(dentistaModel);
    }

    public DentistaModel buscarPorId(Integer id){
        return dentistaRepository.findById(id).get();
    }

    public List<DentistaModel> buscarTodos(){
        return dentistaRepository.findAll();
    }


    public void deletar(Integer id){

        dentistaRepository.deleteById(id);
    }

}
