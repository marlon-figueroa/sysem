package org.primefaces.diamond.dao;

import org.primefaces.diamond.entity.Categoria;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class CategorioaDAO extends GenericDAO<Categoria> {

    public CategorioaDAO() {
        super(Categoria.class);
    }
}
