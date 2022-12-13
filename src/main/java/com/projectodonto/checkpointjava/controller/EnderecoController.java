package com.projectodonto.checkpointjava.controller;

import com.projectodonto.checkpointjava.model.Endereco;
import com.projectodonto.checkpointjava.model.dto.EnderecoDTO;
import com.projectodonto.checkpointjava.service.EnderecoService;
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
@RequestMapping("/endereco")
public class EnderecoController {
    @Autowired
    EnderecoService service;

    @Operation
    @PostMapping
    public ResponseEntity salvarEndereco(@RequestBody Endereco endereco) throws SQLException {
        return new  ResponseEntity(service.salvar(endereco),HttpStatus.OK) ;
    }
    @GetMapping
    public ResponseEntity  buscarEnderecos() throws SQLException {
        return new ResponseEntity(service.buscarTodos(),HttpStatus.OK) ;
    }
    @PatchMapping
    public  ResponseEntity alterar(@RequestBody Endereco endereco){
        if (!service.alterar(endereco)){
            return new ResponseEntity<>("Endereço não encontrado", HttpStatus.NOT_FOUND);
        }

       return new  ResponseEntity("Alterado Endereco de Id: "+endereco.getId(), HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity excluir(@RequestParam("id") Long id){
        if (!service.excluir(id)){
            return new ResponseEntity("Endereço não encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("Excluido endereco de ID: "+id,HttpStatus.OK);
    }
    @RequestMapping(value = "/buscaid", method = RequestMethod.GET)
    public ResponseEntity buscarPorId(@RequestParam("id")Long id)throws SQLException{
        ObjectMapper mapper = new ObjectMapper();
        Optional<Endereco> enderecoOptional = service.buscaPorId(id);
        if (enderecoOptional.isEmpty()){
            return new ResponseEntity("Endereco não encontrado", HttpStatus.NOT_FOUND);
        }
        Endereco endereco = enderecoOptional.get();
        EnderecoDTO enderecoDTO = mapper.convertValue(endereco, EnderecoDTO.class);
        return new ResponseEntity(enderecoDTO, HttpStatus.OK);
    }
}
