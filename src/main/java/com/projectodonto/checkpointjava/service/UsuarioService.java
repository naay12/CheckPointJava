package com.projectodonto.checkpointjava.service;

import com.projectodonto.checkpointjava.model.Usuario;
import com.projectodonto.checkpointjava.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository repository;

    public Usuario salvar(Usuario usuario){
        return repository.save(usuario);
    }
    public List<Usuario> buscarTodos(){
        List<Usuario> listUsuarios = repository.findAll();
        return listUsuarios;
    }
    public Optional<Usuario> buscaPorId(Long id){
        return repository.findById(id);
    }
    public Boolean alterar(Usuario usuario){
        if (repository.findById(usuario.getId()).isPresent()){
            return true;
        }
        return false;
    }
    public Boolean excluir(Long id){
        if (repository.findById(id).isPresent()){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

}
