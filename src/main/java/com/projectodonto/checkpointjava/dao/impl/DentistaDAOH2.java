package com.projectodonto.checkpointjava.dao.impl;

import com.projectodonto.checkpointjava.dao.ConfigurationJDBC;
import com.projectodonto.checkpointjava.model.Dentista;
import com.projectodonto.checkpointjava.dao.IDao;
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
public class DentistaDAOH2 implements IDao<Dentista> {

    private static final Logger logger = LogManager.getLogger(PacienteDAOH2.class);

    ConfigurationJDBC configurationJDBC = new ConfigurationJDBC("org.h2.Driver", "jdbc:h2:~/ChekPointJava;INIT=RUNSCRIPT FROM 'src/main/resources/create.sql'", "sa", "");
    Connection connection = null;


    @Override
    public Dentista salvar(Dentista dentista) throws SQLException {
        String SQLINSERT = String.format("INSERT INTO DENTISTAS (nome, sobrenome, matricula) VALUES('%s','%s','%s')",
                dentista.getNome(), dentista.getSobrenome(), dentista.getMatricula());

        try {
            connection = configurationJDBC.getConnection();
            logger.info("conexao aberta");
            Statement stmt = connection.createStatement();

            stmt.execute(SQLINSERT, stmt.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            logger.info("dados inseridos com sucesso");

            if(rs.next()){
                //dentista.setId(rs.getInt(1));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
            logger.info("fechando conexao");

        }
        return dentista;
    }

    @Override
    public List<Dentista> buscarTodos() throws SQLException {
        String SQLQUERY = "SELECT * FROM DENTISTAS";
        List<Dentista> dentistaList = new ArrayList<>();
        try {
            connection = configurationJDBC.getConnection();
            logger.info("conexao aberta");
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(SQLQUERY);
            logger.info("processando consulta");

            while (rs.next()){
                dentistaList.add(criarObjetoDentista(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
            logger.info("FECHANDO CONEXAO");
        }
        return dentistaList;
    }

    @Override
    public void alterar(Dentista dentista) throws SQLException {
        String SQLUPDATE = String.format("UPDATE DENTISTAS SET matricula = '%s' WHERE idDentista = '%s';",dentista.getMatricula(),dentista.getId());
        try {
            connection = configurationJDBC.getConnection();
            logger.info("conexao aberta");
            Statement stmt = connection.createStatement();
            logger.info("Matricula alterada");
            stmt.execute(SQLUPDATE);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
            logger.info("FECHANDO CONEXAO");
        }

    }

    @Override
    public void excluir(Dentista dentista) throws SQLException {
        String SQLDELETE = String.format("DELETE FROM DENTISTAS WHERE idDentista = '%s';", dentista.getId());

        try {
            connection = configurationJDBC.getConnection();
            logger.info("conexao aberta");
            Statement stmt = connection.createStatement();
            stmt.execute(SQLDELETE);
            logger.info("Dentista deletado");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
            logger.info("FECHANDO CONEXAO");
        }

    }

    @Override
    public Optional<Dentista> buscarPorId(int id) throws SQLException {
        String SQLBUSCARID = String.format("SELECT * FROM DENTISTAS WHERE idDentista = '%s';", id);
        Dentista dentista = null;
        try{
            logger.info("conexao iniciada");
            connection = configurationJDBC.getConnection();
            Statement stmt = connection.createStatement();
            logger.info("Buscando Dentista por ID");
            ResultSet resultado = stmt.executeQuery(SQLBUSCARID);
            while (resultado.next()){
                dentista = criarObjetoDentista(resultado);
            }

        }catch (Exception e){
            throw  new RuntimeException();
        }finally{
            connection.close();
            logger.info("FECHANDO CONEXAO");
        }
        return dentista != null ? Optional.of(dentista): Optional.empty();
    }

    public Dentista criarObjetoDentista(ResultSet rs) throws SQLException {

        String nome = rs.getString("nome");
        String sobrenome = rs.getString("sobrenome");
        String matricula = rs.getString("matricula");

        return new Dentista(nome, sobrenome,matricula);
    }


}
