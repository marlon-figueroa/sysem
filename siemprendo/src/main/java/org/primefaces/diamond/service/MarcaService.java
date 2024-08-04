/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.primefaces.diamond.service;

import java.text.MessageFormat;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;
import org.primefaces.diamond.dao.MarcaDAO;
import org.primefaces.diamond.entity.Marca;

/**
 *
 * @author marlo
 */
@Named
@ApplicationScoped
public class MarcaService implements IService<Marca> {

    private static final Logger LOGGER = Logger.getLogger(MarcaService.class);

    @Inject
    private MarcaDAO dao;

    @Override
    public boolean create(Marca entity) {
        LOGGER.info(MessageFormat.format("Operacion de creacion de {0}", entity.getNombre()));
        return this.dao.create(entity);
    }

    @Override
    public boolean edit(Marca entity) {
        LOGGER.info(MessageFormat.format("Operacion de edicion de {0}", entity.getNombre()));
        return this.dao.edit(entity);
    }

    @Override
    public boolean remove(Marca entity) {
        LOGGER.info(MessageFormat.format("Operacion de remover de {0}", entity.getNombre()));
        return this.dao.remove(entity);
    }

    @Override
    public List<Marca> findAll() {
        LOGGER.info("Operacion de listar categorias");
        return this.dao.findAll();
    }

    @Override
    public Marca findById(int id) {
        LOGGER.info(MessageFormat.format("Operacion de obtener categoria por ID {0}", id));
        return this.dao.find(id);
    }

}
