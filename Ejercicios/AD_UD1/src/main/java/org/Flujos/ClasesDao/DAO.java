package org.Flujos.ClasesDao;

import java.util.List;

public interface DAO<T, K> {

    // Métod para obtener un objeto por su ID
    T get(K id);

    // Métod para obtener todos los objetos
    List<T> getAll();

    // Métod para guardar un nuevo objeto
    boolean save(T objeto);

    // Métod para eliminar un objeto
    boolean delete(T obj);

    // Métod para eliminar todos los objetos
    boolean deleteAll();

    // Métod para eliminar un objeto por su ID
    boolean deleteById(K id);

    // Métod para actualizar un objeto
    void update(T obj);
}