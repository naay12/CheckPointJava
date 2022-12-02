package com.example.medicamento1.service;

import com.example.medicamento1.dao.IDao;
import com.example.medicamento1.model.MedicamentoModel;

public class MedicamentoService {

    private IDao<MedicamentoModel> medicamentoModelIDao;

    public MedicamentoService(IDao<MedicamentoModel> medicamentoModelIDao) {
        this.medicamentoModelIDao = medicamentoModelIDao;
    }

    public MedicamentoModel salvar(MedicamentoModel medicamentoModel){
        return medicamentoModelIDao.salvar(medicamentoModel);
    }
    public MedicamentoModel buscarPorId(Integer id){

        return medicamentoModelIDao.buscarPorId(id);
    }
}
