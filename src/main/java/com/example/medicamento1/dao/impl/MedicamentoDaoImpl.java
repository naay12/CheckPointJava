package com.example.medicamento1.dao.impl;

import com.example.medicamento1.dao.ConfiguracaoJDBC;
import com.example.medicamento1.dao.IDao;
import com.example.medicamento1.model.MedicamentoModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MedicamentoDaoImpl implements IDao<MedicamentoModel> {


    private ConfiguracaoJDBC configuracaoJDBC;

    public MedicamentoDaoImpl(ConfiguracaoJDBC configuracaoJDBC) {

        this.configuracaoJDBC = configuracaoJDBC;
    }

    @Override
    public MedicamentoModel salvar(MedicamentoModel medicamentoModel) {
        Connection connection = configuracaoJDBC.conectarComBancoDeDados();
        Statement statement = null;
        String query = String.format("INSERT INTO medicamentos(nome,laboratorio,quantidade,preco) VALUES('%s','%s','%s','%s')",
        medicamentoModel.getNome(),medicamentoModel.getLaboratorio(),medicamentoModel.getQuantidade(),medicamentoModel.getPreco());
        try{
            statement = connection.createStatement();
            statement.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = statement.getGeneratedKeys();
            if(keys.next())
                medicamentoModel.setId(keys.getInt(1));
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicamentoModel;
    }

    @Override
    public MedicamentoModel buscarPorId(Integer id) {
        Connection connection = configuracaoJDBC.conectarComBancoDeDados();
        Statement statement = null;
        String query = String.format("SELECT * FROM medicamentos WHERE id = '%s'",id);
        MedicamentoModel medicamentoModel = new MedicamentoModel();
        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                medicamentoModel.setId(resultSet.getInt("id"));
                medicamentoModel.setNome(resultSet.getString("nome"));
                medicamentoModel.setLaboratorio(resultSet.getString("laboratorio"));
                medicamentoModel.setQuantidade(resultSet.getInt("quantidade"));
                medicamentoModel.setPreco(resultSet.getDouble("preco"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicamentoModel;
    }
}
