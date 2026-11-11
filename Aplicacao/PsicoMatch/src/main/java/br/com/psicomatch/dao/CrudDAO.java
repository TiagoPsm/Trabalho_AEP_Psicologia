package br.com.psicomatch.dao;

import java.util.List;

public interface CrudDAO<T, ID> {
    void inserir(T objeto) throws Exception;
    List<T> listar() throws Exception;
    T buscar(ID id) throws Exception;
    void atualizar(T objeto) throws Exception;
    void excluir(ID id) throws Exception;
}
