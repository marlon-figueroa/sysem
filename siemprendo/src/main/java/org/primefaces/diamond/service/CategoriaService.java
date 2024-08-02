package org.primefaces.diamond.service;

import java.text.MessageFormat;
import java.util.List;
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

    @Override
    public boolean create(Categoria entity) {
        LOGGER.info(MessageFormat.format("Operacion de creacion de {0}", entity.getNombre()));
        return this.dao.create(entity);
    }

    @Override
    public boolean edit(Categoria entity) {
        LOGGER.info(MessageFormat.format("Operacion de edicion de {0}", entity.getNombre()));
        return this.dao.edit(entity);
    }

    @Override
    public boolean remove(Categoria entity) {
        LOGGER.info(MessageFormat.format("Operacion de remover de {0}", entity.getNombre()));
        return this.dao.remove(entity);
    }

    @Override
    public List<Categoria> findAll() {
        LOGGER.info("Operacion de listar categorias");
        return this.dao.findAll();
    }

    @Override
    public Categoria findById(int id) {
        LOGGER.info(MessageFormat.format("Operacion de obtener categoria por ID {0}", id));
        return this.dao.find(id);
    }

}
