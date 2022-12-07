package com.projectodonto.checkpointjava.service;

import com.projectodonto.checkpointjava.model.Dentista;
import com.projectodonto.checkpointjava.model.dto.DentistaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional

class DentistaServiceTest {

    @Autowired
    DentistaService dentistaService;
    static Dentista dentistaTeste;

    @BeforeEach
    void doBefore(){

       dentistaTeste = new Dentista("Leandro","Pires","654215");

    }
    @Test
    void testeSalvar(){
        dentistaTeste = dentistaService.salvar(dentistaTeste);
        assertTrue(dentistaTeste.getId() >0);
    }

    @Test
    void testeBuscaId() throws SQLException {
        dentistaTeste = dentistaService.salvar(dentistaTeste);

        Optional<Dentista> result = dentistaService.buscarDentistaId(dentistaTeste.getId());
        assertEquals(dentistaTeste, result.orElse(null));
    }

    @Test
    void testeListarDentistas(){
        Dentista dentistaTeste2 = new Dentista("teste2","teste2","5465465");
        dentistaService.salvar(dentistaTeste2);
        dentistaTeste = dentistaService.salvar(dentistaTeste);

        List<DentistaDTO> result = dentistaService.listarDentistas();
        assertTrue(result.size() > 1);
    }
    @Test
    void testeExcluir() throws SQLException {
        dentistaTeste = dentistaService.salvar(dentistaTeste);
        dentistaService.excluir(dentistaTeste.getId());

        assertFalse(dentistaService.buscarDentistaId(dentistaTeste.getId()).isPresent());
    }

    @Test
    void testeAlterar() throws SQLException {

        dentistaTeste = dentistaService.salvar(dentistaTeste);

        String novoNome = "carlos";
        String novaMatricula = "2123456";

        dentistaTeste.setNome(novoNome);
        dentistaTeste.setMatricula(novaMatricula);
        dentistaService.alterar(dentistaTeste);

        Optional<Dentista> result = dentistaService.buscarDentistaId(dentistaTeste.getId());
        Dentista dentistaAlterado = result.get();
        assertSame(dentistaAlterado.getNome(), novoNome);
        assertSame(dentistaAlterado.getMatricula(), novaMatricula);

    }

}