package org.primefaces.diamond.service;

import org.apache.log4j.Logger;
import org.primefaces.diamond.dao.CategorioaDAO;
import org.primefaces.diamond.entity.Categoria;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class CategoriaService implements IService<Categoria> {

    private static final Logger LOGGER = Logger.getLogger(CategoriaService.class);

    @Inject
    private CategorioaDAO dao;

    public boolean create(Categoria entity) {
        return this.dao.create(entity);
    }

}
