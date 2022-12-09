package com.projectodonto.checkpointjava.service;

import com.projectodonto.checkpointjava.model.Consulta;
import com.projectodonto.checkpointjava.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {
    @Autowired
    ConsultaRepository repository;
    @Autowired
    DentistaService dentistaService;

    public Consulta salvar(Consulta consulta) throws SQLException {
        System.out.println(buscarTodos());
        return repository.save(consulta);
    }

    public List<Consulta> buscarTodos(){
        List<Consulta> consultaList = repository.findAll();

        return consultaList;
    }

    public void alterar(Consulta consulta){
        repository.save(consulta);
    }

    public void excluir(Long id){
        repository.deleteById(id);
    }

    public Optional<Consulta> buscarPorId(Long id){
        return repository.findById(id);
    }

}
