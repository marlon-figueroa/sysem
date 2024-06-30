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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author marlo
 */
@Entity
@Table(name = "permiso")
@NamedQueries({
    @NamedQuery(name = "Permiso.findAll", query = "SELECT p FROM Permiso p"),
    @NamedQuery(name = "Permiso.findById", query = "SELECT p FROM Permiso p WHERE p.id = :id"),
    @NamedQuery(name = "Permiso.findByNombre", query = "SELECT p FROM Permiso p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Permiso.findByIcono", query = "SELECT p FROM Permiso p WHERE p.icono = :icono"),
    @NamedQuery(name = "Permiso.findByRuta", query = "SELECT p FROM Permiso p WHERE p.ruta = :ruta"),
    @NamedQuery(name = "Permiso.findByPermisoParent", query = "SELECT p FROM Permiso p WHERE p.permisoParent = :permisoParent"),
    @NamedQuery(name = "Permiso.findByEstado", query = "SELECT p FROM Permiso p WHERE p.estado = :estado")})
public class Permiso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "icono")
    private String icono;
    @Basic(optional = false)
    @Column(name = "ruta")
    private String ruta;
    @Column(name = "permiso_parent")
    private Integer permisoParent;
    @Basic(optional = false)
    @Column(name = "estado")
    private short estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "permisoId", fetch = FetchType.LAZY)
    private List<PermisoRol> permisoRolList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "permiso1", fetch = FetchType.LAZY)
    private Permiso permiso;
    @JoinColumn(name = "id", referencedColumnName = "permiso_parent", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Permiso permiso1;

    public Permiso() {
    }

    public Permiso(Integer id) {
        this.id = id;
    }

    public Permiso(Integer id, String ruta, short estado) {
        this.id = id;
        this.ruta = ruta;
        this.estado = estado;
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

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Integer getPermisoParent() {
        return permisoParent;
    }

    public void setPermisoParent(Integer permisoParent) {
        this.permisoParent = permisoParent;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public List<PermisoRol> getPermisoRolList() {
        return permisoRolList;
    }

    public void setPermisoRolList(List<PermisoRol> permisoRolList) {
        this.permisoRolList = permisoRolList;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

    public Permiso getPermiso1() {
        return permiso1;
    }

    public void setPermiso1(Permiso permiso1) {
        this.permiso1 = permiso1;
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
        if (!(object instanceof Permiso)) {
            return false;
        }
        Permiso other = (Permiso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.primefaces.diamond.domain.Permiso[ id=" + id + " ]";
    }
    
}
