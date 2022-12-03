package com.example.medicamento1.controller;


import com.example.medicamento1.dao.ConfiguracaoJDBC;
import com.example.medicamento1.dao.impl.DentistaDaoImpl;
import com.example.medicamento1.model.DentistaModel;
import com.example.medicamento1.service.DentistaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dentistas")
public class DentistaController {

    private DentistaService dentistaService = new DentistaService(new DentistaDaoImpl(new ConfiguracaoJDBC()));

    @PostMapping("/salvar")
    public DentistaModel salvar(@RequestBody DentistaModel dentistaModel){
        return dentistaService.salvar(dentistaModel);

    }

    @GetMapping("/buscar")
    public List<DentistaModel> listarTodos(){
        return dentistaService.buscarTodos();
    }

    @GetMapping("/{id}")
    public DentistaModel buscarPorId(@PathVariable Integer id){
        return dentistaService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Integer id){
        ResponseEntity responseEntity = null;

        if(dentistaService.deletar(id))
            responseEntity = ResponseEntity.status(HttpStatus.OK).build();
        else
            responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return responseEntity;
    }
}
