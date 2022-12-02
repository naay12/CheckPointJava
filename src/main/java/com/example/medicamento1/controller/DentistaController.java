package com.example.medicamento1.controller;


import com.example.medicamento1.dao.ConfiguracaoJDBC;
import com.example.medicamento1.dao.impl.DentistaDaoImpl;
import com.example.medicamento1.model.DentistaModel;
import com.example.medicamento1.service.DentistaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dentistas")
public class DentistaController {

    private DentistaService dentistaService = new DentistaService(new DentistaDaoImpl(new ConfiguracaoJDBC()));

    @PostMapping("/salvar")
    public DentistaModel salvar(@RequestBody DentistaModel dentistaModel){
        return dentistaService.salvar(dentistaModel);
    }

    @GetMapping("/{id}")
    public DentistaModel buscarPorId(@PathVariable Integer id){
        return dentistaService.buscarPorId(id);
    }
}
