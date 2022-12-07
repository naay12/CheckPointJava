package com.projectodonto.checkpointjava.service;

import com.projectodonto.checkpointjava.model.Endereco;
import com.projectodonto.checkpointjava.model.dto.EnderecoDTO;
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

class EnderecoServiceTest {

    @Autowired
    EnderecoService enderecoService;

    static Endereco enderecoTeste;

    @BeforeEach
    void doBefore(){
        enderecoTeste = new Endereco("RJ","Rio de Janeiro","centro","56456432165","qualquer rua","20");
    }

    @Test
    void testeSalvar(){
        enderecoTeste = enderecoService.salvar(enderecoTeste);
        assertTrue(enderecoTeste.getId() > 0);
    }

    @Test
    void testeBuscaId(){
        enderecoTeste = enderecoService.salvar(enderecoTeste);

        Optional<Endereco> result = enderecoService.buscaPorId(enderecoTeste.getId());
        assertEquals(enderecoTeste, result.orElse(null));
    }

    @Test
    void testeBuscarTodos() throws SQLException {
        Endereco enderecoTeste2 = new Endereco("SP","Sao Paulo","centro","1325641564","alguma rua","15");
        enderecoService.salvar(enderecoTeste2);
        enderecoTeste = enderecoService.salvar(enderecoTeste);

        List<EnderecoDTO> result = enderecoService.buscarTodos();
        assertTrue(result.size() >1);
    }

    @Test
    void testeExluir(){
        enderecoTeste = enderecoService.salvar(enderecoTeste);
        enderecoService.excluir(enderecoTeste.getId());

        assertFalse(enderecoService.buscaPorId(enderecoTeste.getId()).isPresent());
    }

    @Test
    void testeAlterar(){
        enderecoTeste = enderecoService.salvar(enderecoTeste);

        String novoEstado = "MG";
        String novoCidade = "Minas Getais";

        enderecoTeste.setEstado(novoEstado);
        enderecoTeste.setCidade(novoCidade);
        enderecoService.alterar(enderecoTeste);

        Optional<Endereco> result = enderecoService.buscaPorId(enderecoTeste.getId());
        Endereco enderecoAlterado = result.get();
        assertSame(enderecoAlterado.getEstado(),novoEstado);
        assertSame(enderecoAlterado.getCidade(),novoCidade);
    }
}