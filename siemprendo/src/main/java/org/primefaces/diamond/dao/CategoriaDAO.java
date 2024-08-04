package org.primefaces.diamond.dao;

import org.primefaces.diamond.entity.Categoria;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class CategoriaDAO extends GenericDAO<Categoria> {

    public CategoriaDAO() {
        super(Categoria.class);
    }
}
