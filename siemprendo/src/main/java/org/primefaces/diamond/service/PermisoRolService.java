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
import org.primefaces.diamond.dao.PermisoRolDAO;
import org.primefaces.diamond.entity.PermisoRol;

/**
 *
 * @author marlo
 */
@Named
@ApplicationScoped
public class PermisoRolService implements IService<PermisoRol, Integer> {

    private static final Logger LOGGER = Logger.getLogger(PermisoRolService.class);

    @Inject
    private PermisoRolDAO dao;

    @Override
    public boolean create(PermisoRol entity) {
        LOGGER.info(MessageFormat.format("Operacion de creacion de {0}", entity.getId()));
        return this.dao.create(entity);
    }

    @Override
    public boolean edit(PermisoRol entity) {
        LOGGER.info(MessageFormat.format("Operacion de edicion de {0}", entity.getId()));
        return this.dao.edit(entity);
    }

    @Override
    public boolean remove(PermisoRol entity) {
        LOGGER.info(MessageFormat.format("Operacion de remover de {0}", entity.getId()));
        return this.dao.remove(entity);
    }

    @Override
    public List<PermisoRol> findAll() {
        LOGGER.info("Operacion de listar registros");
        return this.dao.findAll();
    }

    @Override
    public PermisoRol findById(Integer id) {
        LOGGER.info(MessageFormat.format("Operacion de obtener el registro por ID {0}", id));
        return this.dao.find(id);
    }

}
