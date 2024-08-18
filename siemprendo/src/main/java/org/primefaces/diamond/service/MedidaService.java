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
import org.primefaces.diamond.dao.MedidaDAO;
import org.primefaces.diamond.entity.Medida;

/**
 *
 * @author marlo
 */
@Named
@ApplicationScoped
public class MedidaService implements IService<Medida, Integer> {

    private static final Logger LOGGER = Logger.getLogger(MedidaService.class);

    @Inject
    private MedidaDAO dao;

    @Override
    public boolean create(Medida entity) {
        LOGGER.info(MessageFormat.format("Operacion de creacion de {0}", entity.getNombre()));
        return this.dao.create(entity);
    }

    @Override
    public boolean edit(Medida entity) {
        LOGGER.info(MessageFormat.format("Operacion de edicion de {0}", entity.getNombre()));
        return this.dao.edit(entity);
    }

    @Override
    public boolean remove(Medida entity) {
        LOGGER.info(MessageFormat.format("Operacion de remover de {0}", entity.getNombre()));
        return this.dao.remove(entity);
    }

    @Override
    public List<Medida> findAll() {
        LOGGER.info("Operacion de listar registros");
        return this.dao.findAll();
    }

    @Override
    public Medida findById(Integer id) {
        LOGGER.info(MessageFormat.format("Operacion de obtener el registro por ID {0}", id));
        return this.dao.find(id);
    }

}
