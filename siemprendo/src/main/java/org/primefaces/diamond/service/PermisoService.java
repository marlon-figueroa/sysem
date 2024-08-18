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
import org.primefaces.diamond.dao.PermisoDAO;
import org.primefaces.diamond.entity.Permiso;

/**
 *
 * @author marlo
 */
@Named
@ApplicationScoped
public class PermisoService implements IService<Permiso, Integer> {

    private static final Logger LOGGER = Logger.getLogger(PermisoService.class);

    @Inject
    private PermisoDAO dao;

    @Override
    public boolean create(Permiso entity) {
        LOGGER.info(MessageFormat.format("Operacion de creacion de {0}", entity.getNombre()));
        return this.dao.create(entity);
    }

    @Override
    public boolean edit(Permiso entity) {
        LOGGER.info(MessageFormat.format("Operacion de edicion de {0}", entity.getNombre()));
        return this.dao.edit(entity);
    }

    @Override
    public boolean remove(Permiso entity) {
        LOGGER.info(MessageFormat.format("Operacion de remover de {0}", entity.getNombre()));
        return this.dao.remove(entity);
    }

    @Override
    public List<Permiso> findAll() {
        LOGGER.info("Operacion de listar registros");
        return this.dao.findAll();
    }

    @Override
    public Permiso findById(Integer id) {
        LOGGER.info(MessageFormat.format("Operacion de obtener el registro por ID {0}", id));
        return this.dao.find(id);
    }

}
