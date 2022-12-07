/*
package com.example.medicamento1.dao.impl;

import com.example.medicamento1.dao.ConfiguracaoJDBC;
import com.example.medicamento1.dao.IdaoDentista;
import com.example.medicamento1.model.DentistaModel;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DentistaDaoImpl implements IdaoDentista<DentistaModel> {
    private ConfiguracaoJDBC configuracaoJDBC;




    public DentistaDaoImpl(ConfiguracaoJDBC configuracaoJDBC) {
        this.configuracaoJDBC = configuracaoJDBC;
    }

    @Override
    public DentistaModel salvar(DentistaModel dentistaModel) {
        Connection connection = configuracaoJDBC.conectarComBancoDeDados();

        Statement statement = null;

        String SQLINSERT = String.format("INSERT INTO DENTISTA (ID ,ATENDE_CONVENIO, EMAIL , NOME,  NUM_MATRICULA) VALUES( '%s','%s','%s','%s','%s')" ,
                dentistaModel.getId(), dentistaModel.getAtendeConvenio() ,dentistaModel.getEmail(), dentistaModel.getNome() ,dentistaModel.getNumMatricula()) ;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(SQLINSERT ,Statement.RETURN_GENERATED_KEYS);
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

    @Override
    public Boolean deletar(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String delete = String.format("DELETE FROM DENTISTA WHERE id = '%s'",id);

        try{
            connection = configuracaoJDBC.conectarComBancoDeDados();
            preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();
            preparedStatement.close();




        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<DentistaModel> buscarTodos() {


        Connection connection = configuracaoJDBC.conectarComBancoDeDados();
        Statement statement = null;
        String SelectAll = String.format("SELECT * FROM DENTISTA" );
        List<DentistaModel> dentistas = new ArrayList<>();

        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SelectAll);
            while(resultSet.next()) {
                 int dentistaId =  resultSet.getInt("ID");
                String nomeDentista = resultSet.getString("NOME");
                String emailDentista = resultSet.getString("EMAIL");
                String NumMatricula = resultSet.getString("NUMMATRICULA");
                int atendeConvenio =resultSet.getInt("ATENDECONVENIO");

                DentistaModel dentistaModel = new DentistaModel(dentistaId,nomeDentista,emailDentista,NumMatricula,atendeConvenio);
                dentistas.add(dentistaModel);


            }
            statement.close();


    } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return null;
    }

    @Override
    public DentistaModel atualizar(DentistaModel dentistaModel) {
        return null;
    }

}
*/



