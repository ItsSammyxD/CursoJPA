/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package curso.jpa.entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author administrador
 */
@Entity
@Table(name = "tbPersona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbPersona.findAll", query = "SELECT t FROM TbPersona t")
    , @NamedQuery(name = "TbPersona.findByIdPersona", query = "SELECT t FROM TbPersona t WHERE t.idPersona = :idPersona")
    , @NamedQuery(name = "TbPersona.findByNombres", query = "SELECT t FROM TbPersona t WHERE t.nombres = :nombres")
    , @NamedQuery(name = "TbPersona.findByApellidos", query = "SELECT t FROM TbPersona t WHERE t.apellidos = :apellidos")
    , @NamedQuery(name = "TbPersona.findByEdad", query = "SELECT t FROM TbPersona t WHERE t.edad = :edad")
    , @NamedQuery(name = "TbPersona.findByTelefono", query = "SELECT t FROM TbPersona t WHERE t.telefono = :telefono")})
public class TbPersona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPersona")
    private Integer idPersona;
    @Basic(optional = false)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "edad")
    private Integer edad;
    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;

    public TbPersona() {
    }

    public TbPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public TbPersona(Integer idPersona, String nombres, String apellidos, String telefono) {
        this.idPersona = idPersona;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbPersona)) {
            return false;
        }
        TbPersona other = (TbPersona) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "curso.jpa.entidad.TbPersona[ idPersona=" + idPersona + " ]";
    }
    
}
