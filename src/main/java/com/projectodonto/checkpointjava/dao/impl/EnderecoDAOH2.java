package com.projectodonto.checkpointjava.dao.impl;

import com.projectodonto.checkpointjava.dao.ConfigurationJDBC;
import com.projectodonto.checkpointjava.dao.IDao;
import com.projectodonto.checkpointjava.model.Endereco;
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
public class EnderecoDAOH2 implements IDao<Endereco> {


    private static final Logger logger = LogManager.getLogger(PacienteDAOH2.class);
    ConfigurationJDBC configurationJDBC = new ConfigurationJDBC("org.h2.Driver", "jdbc:h2:~/ChekPointJava;INIT=RUNSCRIPT FROM 'src/main/resources/create.sql'", "sa", "");
    Connection connection = null;
    @Override
    public Endereco salvar(Endereco endereco) throws SQLException {
        logger.info("Abrindo conexão");
        String SQLInsert = String.format("INSERT INTO enderecos (estado, cidade, bairro, cep, rua, numero)" +
                " VALUES ('%s','%s','%s','%s','%s','%s')", endereco.getEstado(), endereco.getCidade(), endereco.getBairro(), endereco.getCep(),endereco.getRua(),endereco.getNumero());
        Connection connection = null;
        try {
            logger.info("Salvando novo endereço");
            //configurationJDBC = new ConfigurationJDBC("org.h2.Driver","jdbc:h2:~/ChekPointJava;INIT=RUNSCRIPT FROM 'src/main/resources/create.sql'","sa","");
            connection = configurationJDBC.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(SQLInsert, Statement.RETURN_GENERATED_KEYS);

            ResultSet resultSet = statement.getGeneratedKeys();

            if(resultSet.next()){
                endereco.setId(resultSet.getLong(1));
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("Erro ao cadastrar endereço");
        }finally {
            logger.info("Fechando conexão");
            connection.close();
        }
        return endereco;
    }

    @Override
    public List<Endereco> buscarTodos() throws SQLException {
        logger.debug("Abrindo uma conexão no banco");
        Connection connection = null;
        Statement stmt = null;
        String SQLQUERY = "SELECT * FROM ENDERECOS";
        List<Endereco> enderecos = new ArrayList<>();
        try {
            //configurationJDBC = new ConfigurationJDBC("org.h2.Driver","jdbc:h2:~/ChekPointJava;INIT=RUNSCRIPT FROM 'src/main/resources/create.sql'","sa","");
            connection = configurationJDBC.getConnection();
             stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(SQLQUERY);

            logger.debug("Buscando todos os endereços do banco");

            while (rs.next()){
                enderecos.add(criarObjetoEndereco(rs));
            }

        } catch (SQLException throwables) {
           throwables.printStackTrace();
        } finally {
            connection.close();
        }

        return enderecos;
    }

    @Override
    public void alterar(Endereco endereco) throws SQLException {
        String SQLUpdate = String.format("UPDATE ENDERECOS SET ESTADO = '%s', CIDADE = '%s', BAIRRO = '%s', CEP = '%s', RUA = '%s', NUMERO = '%s' where idendereco = '%s';",
                endereco.getEstado(), endereco.getCidade(), endereco.getBairro(), endereco.getCep(),endereco.getRua(), endereco.getNumero(),endereco.getId());
        Connection connection = null;
        try{

            //configurationJDBC = new ConfigurationJDBC("org.h2.Driver","jdbc:h2:~/ChekPointJava;INIT=RUNSCRIPT FROM 'src/main/resources/create.sql'","sa","");
            connection = configurationJDBC.getConnection();
            Statement statement = connection.createStatement();

            statement.execute(SQLUpdate);
            logger.info("Bateu no alterar");
        }catch (Exception e){
            e.printStackTrace();
            logger.error("Erro ao alterar endereco: "+ e.getMessage());
        }finally {
            logger.info("Fechando conexão");
            connection.close();
        }
    }

    @Override
    public void excluir(Endereco endereco) throws SQLException {
        logger.info("Abrindo conexão");
        Connection connection = null;
        Statement stmt = null;
        String SQLDelete = String.format("DELETE FROM ENDERECOS WHERE idendereco = %s", endereco.getId());
        try {
            //configurationJDBC = new ConfigurationJDBC("org.h2.Driver","jdbc:h2:~/ChekPointJava;INIT=RUNSCRIPT FROM 'src/main/resources/create.sql'","sa","");
            connection = configurationJDBC.getConnection();
            logger.debug("Excluindo endereço com id: " + endereco.getId());
            stmt = connection.createStatement();

            stmt.execute(SQLDelete);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            logger.debug("Fechando conexão*");
            connection.close();
        }
    }

    @Override
    public Optional<Endereco> buscarPorId(int id) throws SQLException {
        logger.info("Abrindo conexão;");
        Connection connection = null;
        Statement statement = null;
        String SQLBuscaId = String.format("SELECT * FROM enderecos WHERE idendereco = '%s' ",id);
        Endereco endereco = null;

        try {
            //configurationJDBC = new ConfigurationJDBC("org.h2.Driver","jdbc:h2:~/ChekPointJava;INIT=RUNSCRIPT FROM 'src/main/resources/create.sql'","sa","");
            connection = configurationJDBC.getConnection();
            logger.info("Buscando o endereco com id: "+id);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQLBuscaId);
            while (resultSet.next()){
                endereco = criarObjetoEndereco(resultSet);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }finally {
            logger.info("Fechando conexão com o bando");
            connection.close();
        }

        return endereco != null ? Optional.of(endereco): Optional.empty();
    }

    private Endereco criarObjetoEndereco(ResultSet rs) throws SQLException {

        Long id = rs.getLong("idEndereco");
        String estado = rs.getString("estado");
        String cidade = rs.getString("cidade");
        String bairro = rs.getString("bairro");
        String cep = rs.getString("cep");
        String rua = rs.getString("rua");
        String numero = rs.getString("numero");
        return new Endereco(id,estado, cidade, bairro, cep,rua, numero);

    }
}
