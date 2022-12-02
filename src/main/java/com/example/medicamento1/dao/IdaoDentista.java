package com.example.medicamento1.dao;

public interface IdaoDentista<T> {

    public T salvar(T t);
    public T buscarPorId(Integer id);
}
