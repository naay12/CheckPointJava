package com.example.medicamento1.dao.impl;

import com.example.medicamento1.dao.ConfiguracaoJDBC;
import com.example.medicamento1.dao.IdaoDentista;
import com.example.medicamento1.model.DentistaModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DentistaDaoImpl implements IdaoDentista<DentistaModel> {
    private ConfiguracaoJDBC configuracaoJDBC;

    //ConfiguracaoJDBC configurationJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/Test;INIT=RUNSCRIPT FROM 'src/main/resources/createSQL.sql'","sa","" );


    public DentistaDaoImpl(ConfiguracaoJDBC configuracaoJDBC) {
        this.configuracaoJDBC = configuracaoJDBC;
    }

    @Override
    public DentistaModel salvar(DentistaModel dentistaModel) {
        Connection connection = configuracaoJDBC.conectarComBancoDeDados();

        Statement statement = null;

        String SQLINSERT = String.format("INSERT INTO DENTISTA (NOME, EMAIL, NUMMATRICULA, ATENDECONVENIO) VALUES('%s','%s','%s','%S')",
                dentistaModel.getNome(), dentistaModel.getEmail() ,dentistaModel.getNumMatricula() , dentistaModel.getAtendeConvenio());

        try {
            statement = connection.createStatement();
            statement.executeUpdate(SQLINSERT,Statement.RETURN_GENERATED_KEYS);
            //logger.info("conexao aberta");
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next())
                dentistaModel.setId(keys.getInt(1));
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dentistaModel;
    }


    @Override
    public DentistaModel buscarPorId(Integer id) {

        Connection connection = configuracaoJDBC.conectarComBancoDeDados();
        Statement statement = null;
        String query = String.format("SELECT * FROM DENTISTA WHERE id = '%s'",id);
        DentistaModel dentistaModel = new DentistaModel();

        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                dentistaModel.setId(resultSet.getInt("ID"));
                dentistaModel.setNome(resultSet.getString("NOME"));
                dentistaModel.setEmail(resultSet.getString("EMAIL"));
                dentistaModel.setNumMatricula(resultSet.getString("NUMMATRICULA"));
                dentistaModel.setAtendeConvenio(resultSet.getInt("ATENDECONVENIO"));

            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return dentistaModel;
    }

}



