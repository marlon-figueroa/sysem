/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.primefaces.diamond.domain;

import org.primefaces.diamond.entity.Producto;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author marlo
 */
@Entity
@Table(name = "catalogo_producto")
@NamedQueries({
    @NamedQuery(name = "CatalogoProducto.findAll", query = "SELECT c FROM CatalogoProducto c"),
    @NamedQuery(name = "CatalogoProducto.findByCuenta", query = "SELECT c FROM CatalogoProducto c WHERE c.cuenta = :cuenta")})
public class CatalogoProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cuenta")
    private String cuenta;
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Categoria categoriaId;
    @JoinColumn(name = "categoria_padre", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Categoria categoriaPadre;
    @JoinColumn(name = "producto_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Producto productoId;

    public CatalogoProducto() {
    }

    public CatalogoProducto(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public Categoria getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Categoria categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Categoria getCategoriaPadre() {
        return categoriaPadre;
    }

    public void setCategoriaPadre(Categoria categoriaPadre) {
        this.categoriaPadre = categoriaPadre;
    }

    public Producto getProductoId() {
        return productoId;
    }

    public void setProductoId(Producto productoId) {
        this.productoId = productoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cuenta != null ? cuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatalogoProducto)) {
            return false;
        }
        CatalogoProducto other = (CatalogoProducto) object;
        if ((this.cuenta == null && other.cuenta != null) || (this.cuenta != null && !this.cuenta.equals(other.cuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.primefaces.diamond.domain.CatalogoProducto[ cuenta=" + cuenta + " ]";
    }
    
}
