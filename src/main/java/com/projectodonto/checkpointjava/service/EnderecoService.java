package com.projectodonto.checkpointjava.service;

import com.projectodonto.checkpointjava.model.Endereco;
import com.projectodonto.checkpointjava.model.dto.EnderecoDTO;
import com.projectodonto.checkpointjava.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    @Autowired
    EnderecoRepository repository;
    public Endereco salvar(Endereco endereco)  {



        return repository.save(endereco);
    }
    public List<EnderecoDTO> buscarTodos() throws SQLException{
        List<Endereco> listEndereco = repository.findAll();
        List<EnderecoDTO> listEnderecoDTO = new ArrayList<>();

        for (Endereco e : listEndereco){
            listEnderecoDTO.add(new EnderecoDTO(e));
        }
        return listEnderecoDTO;
    }
    public Boolean alterar(Endereco endereco){

        if ((repository.findById(endereco.getId()).isPresent())){
            repository.save(endereco);
            return true;
        }
        return false;

    }
    public Boolean excluir(Long id)  {
        if (repository.findById(id).isPresent()){
            repository.deleteById(id);
            return true;
        }
        return false;
    }
    public Optional<Endereco> buscaPorId(Long id)  {
        return repository.findById(id);
    }
}
