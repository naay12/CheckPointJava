package com.projectodonto.checkpointjava.controller;

import com.projectodonto.checkpointjava.service.DentistaService;
import com.projectodonto.checkpointjava.model.Dentista;
import com.projectodonto.checkpointjava.model.dto.DentistaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Optional;


@RestController
@RequestMapping("/dentista")
public class DentistaController {

    @Autowired
    DentistaService service;

    @Operation(summary = "Cadastra um novo dentista")
    @PostMapping
    public ResponseEntity salvarDentista(@RequestBody Dentista dentista) {
        return new ResponseEntity(service.salvar(dentista), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity buscarDentistas() throws SQLException{
        return new ResponseEntity(service.listarDentistas(), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity alterarDentista(@RequestBody Dentista dentista){
        service.alterar(dentista);
        return new ResponseEntity(dentista.getId(),HttpStatus.OK );
    }

    @DeleteMapping
    public ResponseEntity deleteDentista(@RequestParam Long id)  {
        service.excluir(id);
        return new ResponseEntity(id +" Excluido.",HttpStatus.OK);
    }
    @RequestMapping( value ="/buscaid", method = RequestMethod.GET)
    public ResponseEntity buscarDentistaId(@RequestParam("id") Long id) throws SQLException {
        ObjectMapper mapper = new ObjectMapper();

        Optional<Dentista> dentistaOptional = service.buscarDentistaId(id);
        if(dentistaOptional.isEmpty()){
            return new ResponseEntity("Dentista nao cadastrado", HttpStatus.NOT_FOUND);
        }
        Dentista dentista = dentistaOptional.get();
        DentistaDTO dentistaDTO = mapper.convertValue(dentista,DentistaDTO.class);

        return new ResponseEntity(dentistaDTO,HttpStatus.OK);
    }

}
