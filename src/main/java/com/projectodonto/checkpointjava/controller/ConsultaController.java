package com.projectodonto.checkpointjava.controller;

import com.projectodonto.checkpointjava.model.Consulta;
import com.projectodonto.checkpointjava.service.ConsultaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Optional;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {
    @Autowired
    ConsultaService service;

    @PostMapping
    public ResponseEntity salvar(@RequestBody Consulta consulta) throws SQLException {
        return new ResponseEntity(service.salvar(consulta), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity buscarTodos() throws SQLException {
        return new ResponseEntity(service.buscarTodos(),HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity alterar(@RequestBody Consulta consulta) throws SQLException {
        service.alterar(consulta);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity excluir(@RequestParam Long id) throws SQLException {
        service.excluir(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/buscarid", method = RequestMethod.GET)
    public ResponseEntity buscarPorId(@RequestParam("id") Long id) throws SQLException {
        ObjectMapper mapper = new ObjectMapper();

        Optional<Consulta> consultaOptional = service.buscarPorId(id);
        if(consultaOptional.isEmpty()){
            return new ResponseEntity("Paciente não foi encontrado", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(consultaOptional, HttpStatus.OK);
    }
}
