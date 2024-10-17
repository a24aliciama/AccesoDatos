package org.Flujos.ClasesDao;

import java.util.List;

public interface DAO<T, K> {

    // obtiene un objeto por su ID
    T get(K id);

    // todos los objetos
    List<T> getAll();

    // guarda un nuevo objeto
    boolean save(T objeto);

    // elimina un objeto
    boolean delete(T obj);

    // elimina todos los objetos
    boolean deleteAll();

    // elimina un objeto por su ID
    boolean deleteById(K id);

    // actualiza un objeto
    void update(T obj);
}