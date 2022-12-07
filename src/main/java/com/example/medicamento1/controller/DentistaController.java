package com.example.medicamento1.controller;


import com.example.medicamento1.Exception.ResourceNotFoundException;
import com.example.medicamento1.model.DentistaModel;
import com.example.medicamento1.service.DentistaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dentistas")
public class DentistaController {

    private final DentistaService dentistaService ;

    public DentistaController(DentistaService dentistaService) {
        this.dentistaService = dentistaService;
    }

    @PostMapping("/salvar")
    public ResponseEntity<DentistaModel> salvar(@RequestBody DentistaModel dentistaModel){
        return ResponseEntity.ok(dentistaService.salvar(dentistaModel));

    }

    @GetMapping("/buscar")
    public List<DentistaModel> listarTodos(){

        return dentistaService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DentistaModel>  buscarPorId(@PathVariable Integer id) throws ResourceNotFoundException{
        try {
            return ResponseEntity.ok(dentistaService.buscarPorId(id));
        }catch (Exception e){
            throw new ResourceNotFoundException("Não encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity deletar(@PathVariable Integer id) throws ResourceNotFoundException{
        ResponseEntity responseEntity = null;

        try{
            dentistaService.deletar(id);
            return ResponseEntity.ok("Deletado");
        }catch (Exception e){
            throw new ResourceNotFoundException("Não encontrado");
        }
    }
}
