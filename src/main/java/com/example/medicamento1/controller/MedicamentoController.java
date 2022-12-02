package com.example.medicamento1.controller;

import com.example.medicamento1.dao.ConfiguracaoJDBC;
import com.example.medicamento1.dao.impl.MedicamentoDaoImpl;
import com.example.medicamento1.model.MedicamentoModel;
import com.example.medicamento1.service.MedicamentoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {

    private MedicamentoService medicamentoService = new MedicamentoService(new MedicamentoDaoImpl(new ConfiguracaoJDBC()));

    @PostMapping("/salvar")
    public MedicamentoModel salvar(@RequestBody MedicamentoModel medicamentoModel){
        return medicamentoService.salvar(medicamentoModel);
    }

    @GetMapping("/{id}")
    public MedicamentoModel buscarPorId(@PathVariable Integer id){

        return medicamentoService.buscarPorId(id);
    }

}
