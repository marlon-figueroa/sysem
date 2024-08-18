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
import org.primefaces.diamond.dao.DetalleVentaDAO;
import org.primefaces.diamond.entity.DetalleVenta;

/**
 *
 * @author marlo
 */
@Named
@ApplicationScoped
public class DetalleVentaService implements IService<DetalleVenta, Integer> {

    private static final Logger LOGGER = Logger.getLogger(DetalleVentaService.class);

    @Inject
    private DetalleVentaDAO dao;

    @Override
    public boolean create(DetalleVenta entity) {
        LOGGER.info(MessageFormat.format("Operacion de creacion de {0}", entity.getId()));
        return this.dao.create(entity);
    }

    @Override
    public boolean edit(DetalleVenta entity) {
        LOGGER.info(MessageFormat.format("Operacion de edicion de {0}", entity.getId()));
        return this.dao.edit(entity);
    }

    @Override
    public boolean remove(DetalleVenta entity) {
        LOGGER.info(MessageFormat.format("Operacion de remover de {0}", entity.getId()));
        return this.dao.remove(entity);
    }

    @Override
    public List<DetalleVenta> findAll() {
        LOGGER.info("Operacion de listar registros");
        return this.dao.findAll();
    }

    @Override
    public DetalleVenta findById(Integer id) {
        LOGGER.info(MessageFormat.format("Operacion de obtener el registro por ID {0}", id));
        return this.dao.find(id);
    }

}
