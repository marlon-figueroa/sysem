/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.primefaces.diamond.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author marlo
 */
@Entity
@Table(name = "producto")
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findById", query = "SELECT p FROM Producto p WHERE p.id = :id"),
    @NamedQuery(name = "Producto.findByNombre", query = "SELECT p FROM Producto p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Producto.findByPrecioUnitario", query = "SELECT p FROM Producto p WHERE p.precioUnitario = :precioUnitario"),
    @NamedQuery(name = "Producto.findByExistencia", query = "SELECT p FROM Producto p WHERE p.existencia = :existencia")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "precio_unitario")
    private Long precioUnitario;
    @Column(name = "existencia")
    private Integer existencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoId", fetch = FetchType.LAZY)
    private List<DetalleVenta> detalleVentaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoId", fetch = FetchType.LAZY)
    private List<Presentacion> presentacionList;
    @JoinColumn(name = "marca_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Marca marcaId;
    @JoinColumn(name = "medida_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Medida medidaId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoId", fetch = FetchType.LAZY)
    private List<CatalogoProducto> catalogoProductoList;

    public Producto() {
    }

    public Producto(Integer id) {
        this.id = id;
    }

    public Producto(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Long precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Integer getExistencia() {
        return existencia;
    }

    public void setExistencia(Integer existencia) {
        this.existencia = existencia;
    }

    public List<DetalleVenta> getDetalleVentaList() {
        return detalleVentaList;
    }

    public void setDetalleVentaList(List<DetalleVenta> detalleVentaList) {
        this.detalleVentaList = detalleVentaList;
    }

    public List<Presentacion> getPresentacionList() {
        return presentacionList;
    }

    public void setPresentacionList(List<Presentacion> presentacionList) {
        this.presentacionList = presentacionList;
    }

    public Marca getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Marca marcaId) {
        this.marcaId = marcaId;
    }

    public Medida getMedidaId() {
        return medidaId;
    }

    public void setMedidaId(Medida medidaId) {
        this.medidaId = medidaId;
    }

    public List<CatalogoProducto> getCatalogoProductoList() {
        return catalogoProductoList;
    }

    public void setCatalogoProductoList(List<CatalogoProducto> catalogoProductoList) {
        this.catalogoProductoList = catalogoProductoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.primefaces.diamond.domain.Producto[ id=" + id + " ]";
    }
    
}
