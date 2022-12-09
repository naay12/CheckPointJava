package com.projectodonto.checkpointjava.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IDao <T>{

    T salvar(T t) throws SQLException;

    List<T> buscarTodos() throws SQLException;

    void alterar(T t) throws SQLException;

    void excluir(T t) throws SQLException;

    public Optional<T> buscarPorId(int id) throws SQLException;
}
