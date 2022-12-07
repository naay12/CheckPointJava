package com.projectodonto.checkpointjava.dao.impl;

import com.projectodonto.checkpointjava.dao.ConfigurationJDBC;
import com.projectodonto.checkpointjava.dao.IDao;
import com.projectodonto.checkpointjava.model.Paciente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
public class PacienteDAOH2 implements IDao<Paciente> {


    private static final Logger logger = LogManager.getLogger(PacienteDAOH2.class);

    ConfigurationJDBC configurationJDBC = new ConfigurationJDBC("org.h2.Driver", "jdbc:h2:~/ChekPointJava;INIT=RUNSCRIPT FROM 'src/main/resources/create.sql'", "sa", "");
    Connection connection = null;

    @Override
    public Paciente salvar(Paciente paciente) throws SQLException {
        String SQLINSERT = String.format("INSERT INTO PACIENTES (nome, sobrenome, telefone, endereco, cpf, dataCadastro) VALUES('%s','%s','%s','%s','%s','%s')",
                paciente.getNome(), paciente.getSobrenome(), paciente.getTelefone(), paciente.getEndereco(), paciente.getCpf(), paciente.getDataCadastro());

        try {
            connection = configurationJDBC.getConnection();
            logger.info("CONEXAO ABERTA");
            Statement stmt = connection.createStatement();

            stmt.execute(SQLINSERT, stmt.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            logger.info("PACIENTE INSERIDO COM SUCESSO");

            if(rs.next()){
                paciente.setId(rs.getLong(1));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
            logger.info("FECHANDO CONEXAO");
        }
        return paciente;
    }

    @Override
    public List<Paciente> buscarTodos() throws SQLException {
        String SQLQUERY = "SELECT * FROM PACIENTES";
        List<Paciente> pacienteList = new ArrayList<>();

        try {
            connection = configurationJDBC.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SQLQUERY);

            while (rs.next()){
                pacienteList.add(criarObjetoPaciente(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }

        return pacienteList;
    }

    @Override
    public void alterar(Paciente paciente) throws SQLException {
        String SQLUPDATE = String.format("UPDATE PACIENTES SET telefone = %s WHERE idPaciente = %s;", paciente.getTelefone(), paciente.getId());

        try {
            connection = configurationJDBC.getConnection();
            Statement stmt = connection.createStatement();
            stmt.execute(SQLUPDATE);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }

    }

    @Override
    public void excluir(Paciente paciente) throws SQLException {
        String SQLDELETE = String.format("DELETE FROM PACIENTES WHERE idPaciente = '%s';", paciente.getId());

        try {
            connection = configurationJDBC.getConnection();
            Statement stmt = connection.createStatement();
            stmt.execute(SQLDELETE);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }

    }
    @Override
    public Optional<Paciente> buscarPorId(int id) throws SQLException {
        String query = String.format("SELECT * FROM pacientes WHERE idPaciente = %s",id);
        Paciente paciente = null;
        try {
            connection = configurationJDBC.getConnection();
            Statement stmt = connection.createStatement();

            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                paciente = criarObjetoPaciente(rs);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            connection.close();
        }

        return paciente != null ? Optional.of(paciente) : Optional.empty();
    }

    private Paciente criarObjetoPaciente(ResultSet rs) throws SQLException {

        Long id = rs.getLong("idPaciente");
        String nome = rs.getString("nome");
        String sobrenome = rs.getString("sobrenome");
        String cpf = rs.getString("cpf");
        return new Paciente(id, nome, sobrenome, cpf);
    }

}
