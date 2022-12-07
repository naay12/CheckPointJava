package com.example.clinicadentista.controller;
import com.example.clinicadentista.Exception.ResourceNotFoundException;
import com.example.clinicadentista.model.PacienteModel;
import com.example.clinicadentista.service.PacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }


    @PostMapping("/salvar")
    public ResponseEntity<PacienteModel> salvar(@RequestBody PacienteModel pacienteModel){
        return ResponseEntity.ok(pacienteService.salvarPaciente(pacienteModel));

    }

    @GetMapping("/buscar")
    public List<PacienteModel> listarTodos(){

        return pacienteService.buscarTodosPaciente();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteModel>  buscarPorId(@PathVariable Integer id) throws ResourceNotFoundException {
        try {
            return ResponseEntity.ok(pacienteService.buscarPorIdPaciente(id));
        }catch (Exception e){
            throw new ResourceNotFoundException("Não encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity deletar(@PathVariable Integer id) throws ResourceNotFoundException{
        ResponseEntity responseEntity = null;

        try{
            pacienteService.deletarPaciente(id);
            return ResponseEntity.ok("Deletado");
        }catch (Exception e){
            throw new ResourceNotFoundException("Não encontrado");
        }
    }
}
