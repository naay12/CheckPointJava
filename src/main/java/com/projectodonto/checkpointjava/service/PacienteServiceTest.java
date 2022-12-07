package com.projectodonto.checkpointjava.service;

import com.projectodonto.checkpointjava.model.Paciente;
import com.projectodonto.checkpointjava.exception.ResourceNotFoundException;
import com.projectodonto.checkpointjava.model.dto.PacienteDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) //Executa o teste por ordem de criação

@SpringBootTest
@Transactional

class PacienteServiceTest {

    @Autowired
    PacienteService pacienteService;

    static Paciente pacienteTeste;

    @BeforeEach
    void doBefore(){

        pacienteTeste = new Paciente("Filho","doBill","5487982314","518.432.145-54");

    }

    @Test
    void testeSalvar(){
        pacienteTeste = pacienteService.salvar(pacienteTeste);
        assertTrue(pacienteTeste.getId() >0);
    }

    @Test
    void testeBuscaId() throws ResourceNotFoundException {
        pacienteTeste = pacienteService.salvar(pacienteTeste);

        PacienteDTO result = pacienteService.buscarPorId(pacienteTeste.getId());
        assertEquals(pacienteTeste.getId(),result.getId());
    }

    @Test
    void testeBuscarTodos(){
        Paciente paciente2 = new Paciente("Mulher","doBill","547182314","547.144.145-54");
        pacienteService.salvar(paciente2);
        pacienteTeste = pacienteService.salvar(pacienteTeste);

        List<PacienteDTO> result = pacienteService.buscarTodos();
        assertTrue(result.size() > 1);
    }
    @Test
    void testeExcluir() throws ResourceNotFoundException {

        pacienteService.excluir(pacienteTeste.getId());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class, ()->pacienteService.buscarPorId(pacienteTeste.getId()));

        assertTrue(exception.getMessage().contains("ID invalido"));

    }

    @Test
    void testeAlterar() throws ResourceNotFoundException {

        pacienteTeste = pacienteService.salvar(pacienteTeste);

        String novoNome = "carlos";
        String novoSobrenome = "doBill";

        pacienteTeste.setNome(novoNome);
        pacienteTeste.setSobrenome(novoSobrenome);
        pacienteService.alterar(pacienteTeste);

        Optional<PacienteDTO> result = Optional.ofNullable(pacienteService.buscarPorId(pacienteTeste.getId()));
        PacienteDTO pacienteAlterado = result.get();
        assertSame(pacienteAlterado.getNome(), novoNome);
        assertSame(pacienteAlterado.getSobrenome(), novoSobrenome);

    }

}