package com.projectodonto.checkpointjava.controller;

import com.projectodonto.checkpointjava.model.Usuario;
import com.projectodonto.checkpointjava.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService service;

    @PostMapping
    public ResponseEntity salvarUsuario(@RequestBody Usuario usuario) throws SQLException {
        return new ResponseEntity(service.salvar(usuario), HttpStatus.OK);

    }
    @GetMapping
    public ResponseEntity buscarUsuarios(){
        return new ResponseEntity<>(service.buscarTodos(),HttpStatus.OK);
    }
    @PatchMapping
    public  ResponseEntity alterar(@RequestBody Usuario usuario){
        if (!service.alterar(usuario)){
            return new ResponseEntity<>("Usuario não encontrado", HttpStatus.NOT_FOUND);
        }

        return new  ResponseEntity("Alterado usuario de ID: "+usuario.getId(), HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity excluir(@RequestParam("id") Long id){
        if (!service.excluir(id)){
            return new ResponseEntity("Usuario não encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("Excluido usuario de ID: "+id,HttpStatus.OK);
    }
    @RequestMapping(value = "/buscaid", method = RequestMethod.GET)
    public ResponseEntity buscarPorId(@RequestParam("id")Long id)throws SQLException{
         Optional<Usuario> usuario = service.buscaPorId(id);
        if (!usuario.isPresent()){
            return new ResponseEntity("Usuario não encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(usuario,HttpStatus.OK);
    }


}
