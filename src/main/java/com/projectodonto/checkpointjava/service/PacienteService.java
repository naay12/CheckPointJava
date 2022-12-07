package com.projectodonto.checkpointjava.service;

import com.projectodonto.checkpointjava.exception.ResourceNotFoundException;
import com.projectodonto.checkpointjava.model.Paciente;
import com.projectodonto.checkpointjava.repository.PacienteRepository;
import com.projectodonto.checkpointjava.model.dto.PacienteDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    PacienteRepository repository;

    public Paciente salvar(Paciente paciente){
        return repository.save(paciente);
    }

//    //public List<Paciente> buscarTodos(){
//        return repository.findAll();
//    }
    public List<PacienteDTO> buscarTodos(){
        List<Paciente> pacienteList = repository.findAll();
        List<PacienteDTO> pacienteDTOList = new ArrayList<>();

        for (Paciente e: pacienteList) {
            pacienteDTOList.add(new PacienteDTO(e));
        }

        return pacienteDTOList;
    }

    public void alterar(Paciente paciente){
        repository.save(paciente);
    }

    public void excluir(Long id) throws ResourceNotFoundException {
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Erro ao excluir produto, id informado não existe"));
        repository.deleteById(id);
    }

//    public Paciente buscarPorId(Long id) throws ResourceNotFoundException {
//        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Erro ao excluir produto, id informado não existe"));
//    }

    public PacienteDTO buscarPorId(Long id) throws ResourceNotFoundException {
        ObjectMapper mapper = new ObjectMapper();

        Optional<Paciente> pacienteOptional = repository.findById(id);
        Paciente paciente = null;

        try {
            paciente = pacienteOptional.get();
        } catch (Exception e) {
            throw new ResourceNotFoundException("ID invalido, este paciente não existe");
        }

        PacienteDTO pacienteDTO = mapper.convertValue(paciente, PacienteDTO.class);

        return pacienteDTO;
    }


}
