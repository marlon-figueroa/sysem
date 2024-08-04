/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.primefaces.diamond.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import org.primefaces.diamond.entity.Marca;

/**
 *
 * @author marlo
 */
@Named
@ApplicationScoped
public class MarcaDAO extends GenericDAO<Marca> {

    public MarcaDAO() {
        super(Marca.class);
    }

}
