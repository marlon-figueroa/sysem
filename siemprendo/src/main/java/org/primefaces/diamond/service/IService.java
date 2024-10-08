package org.primefaces.diamond.service;

import java.util.List;

public interface IService<T, S> {

    boolean create(T entity);

    boolean edit(T entity);

    boolean remove(T entity);

    List<T> findAll();

    T findById(S id);

}
