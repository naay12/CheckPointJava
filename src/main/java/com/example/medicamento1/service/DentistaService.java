package com.example.medicamento1.service;

import com.example.medicamento1.dao.IdaoDentista;
import com.example.medicamento1.model.DentistaModel;


public class DentistaService {

    private IdaoDentista<DentistaModel> dentistaModelIdaoDentista;

    public DentistaService(IdaoDentista<DentistaModel> dentistaModelIdaoDentista) {
        this.dentistaModelIdaoDentista = dentistaModelIdaoDentista;
    }

    public DentistaModel salvar(DentistaModel dentistaModel){
        return dentistaModelIdaoDentista.salvar(dentistaModel);
    }

    public DentistaModel buscarPorId(Integer id){
        return dentistaModelIdaoDentista.buscarPorId(id);
    }

}
