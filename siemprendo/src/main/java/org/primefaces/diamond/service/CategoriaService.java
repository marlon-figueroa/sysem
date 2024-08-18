package org.primefaces.diamond.service;

import java.text.MessageFormat;
import java.util.List;
import org.apache.log4j.Logger;
import org.primefaces.diamond.entity.Categoria;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.diamond.dao.CategoriaDAO;

@Named
@ApplicationScoped
public class CategoriaService implements IService<Categoria, Integer> {

    private static final Logger LOGGER = Logger.getLogger(CategoriaService.class);

    @Inject
    private CategoriaDAO dao;

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
        LOGGER.info("Operacion de listar registros");
        return this.dao.findAll();
    }

    @Override
    public Categoria findById(Integer id) {
        LOGGER.info(MessageFormat.format("Operacion de obtener el registro por ID {0}", id));
        return this.dao.find(id);
    }

}
