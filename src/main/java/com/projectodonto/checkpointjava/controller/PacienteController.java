package com.projectodonto.checkpointjava.controller;

import com.projectodonto.checkpointjava.service.PacienteService;
import com.projectodonto.checkpointjava.model.Paciente;
import com.projectodonto.checkpointjava.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    PacienteService service;

    @Operation(summary = "Cadastrar um novo paciente")
    @PostMapping
    public ResponseEntity salvarPaciente(@RequestBody Paciente paciente) {
        return new ResponseEntity(service.salvar(paciente),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity buscarPacientes() throws SQLException {

        return new ResponseEntity(service.buscarTodos(),HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity alterarPaciente(@RequestBody Paciente paciente) {
        service.alterar(paciente);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestParam Long id) throws ResourceNotFoundException {
        service.excluir(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/buscaid", method = RequestMethod.GET)
    public ResponseEntity buscarPorId(@RequestParam("id") Long id) throws ResourceNotFoundException {

        return new ResponseEntity(service.buscarPorId(id), HttpStatus.OK);
    }

}
