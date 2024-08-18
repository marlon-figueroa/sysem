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
import org.primefaces.diamond.dao.CatalogoProductoDAO;
import org.primefaces.diamond.entity.CatalogoProducto;

/**
 *
 * @author marlo
 */
@Named
@ApplicationScoped
public class CatalogoProductoService implements IService<CatalogoProducto, String> {

    private static final Logger LOGGER = Logger.getLogger(CatalogoProductoService.class);

    @Inject
    private CatalogoProductoDAO dao;

    @Override
    public boolean create(CatalogoProducto entity) {
        LOGGER.info(MessageFormat.format("Operacion de creacion de {0}", entity.getCuenta()));
        return this.dao.create(entity);
    }

    @Override
    public boolean edit(CatalogoProducto entity) {
        LOGGER.info(MessageFormat.format("Operacion de edicion de {0}", entity.getCuenta()));
        return this.dao.edit(entity);
    }

    @Override
    public boolean remove(CatalogoProducto entity) {
        LOGGER.info(MessageFormat.format("Operacion de remover de {0}", entity.getCuenta()));
        return this.dao.remove(entity);
    }

    @Override
    public List<CatalogoProducto> findAll() {
        LOGGER.info("Operacion de listar registros");
        return this.dao.findAll();
    }

    @Override
    public CatalogoProducto findById(String id) {
        LOGGER.info(MessageFormat.format("Operacion de obtener categoria por ID {0}", id));
        return this.dao.find(id);
    }

}
