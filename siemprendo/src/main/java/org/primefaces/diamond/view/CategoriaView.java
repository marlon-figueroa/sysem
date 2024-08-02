/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.primefaces.diamond.view;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.log4j.Logger;
import org.primefaces.diamond.entity.Categoria;
import org.primefaces.diamond.service.CategoriaService;

/**
 *
 * @author marlo
 */
@Named
@ViewScoped
public class CategoriaView implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(CategoriaView.class);

    @Inject
    private CategoriaService categoriaService;

    private List<Categoria> categorias;
    private Categoria selectedCategoria;

    @PostConstruct
    public void init() {
        this.listar();
    }

    public void listar() {
        LOGGER.info("[.] -> Listar...");
        this.categorias = this.categoriaService.findAll();
        LOGGER.info("[.] -> Listar...Ok");
    }

    public void deleteEntity() {
        LOGGER.info("[.] -> deleteEntity...");
        this.categoriaService.remove(this.selectedCategoria);
        this.listar();
        LOGGER.info("[.] -> deleteEntity...Ok");
    }

    public void saveEntity() {
        LOGGER.info("[.] -> saveEntity...");
        if (this.selectedCategoria.getId() == null) {
            this.categoriaService.create(this.selectedCategoria);
        } else {
            this.categoriaService.edit(this.selectedCategoria);
        }
        this.listar();
        LOGGER.info("[.] -> saveEntity...Ok");
    }
    
    public void setSelected() {
        this.selectedCategoria = new Categoria();
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Categoria getSelectedCategoria() {
        return selectedCategoria;
    }

    public void setSelectedCategoria(Categoria selectedCategoria) {
        this.selectedCategoria = selectedCategoria;
    }

}
