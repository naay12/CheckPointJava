package com.example.clinicadentista.service;

import com.example.clinicadentista.model.PacienteModel;
import com.example.clinicadentista.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;


    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public PacienteModel salvarPaciente(PacienteModel pacienteModel){
        return pacienteRepository.save(pacienteModel);
    }

    public PacienteModel buscarPorIdPaciente(Integer id){
        return pacienteRepository.findById(id).get();
    }

    public List<PacienteModel> buscarTodosPaciente(){
        return pacienteRepository.findAll();
    }

    public void deletarPaciente(Integer id){
        pacienteRepository.deleteById(id);
    }


}
