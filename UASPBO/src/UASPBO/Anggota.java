/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UASPBO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author RIO
 */
@Entity
@Table(name = "anggota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Anggota.findAll", query = "SELECT a FROM Anggota a"),
    @NamedQuery(name = "Anggota.findById", query = "SELECT a FROM Anggota a WHERE a.id = :id"),
    @NamedQuery(name = "Anggota.findByNamaMhs", query = "SELECT a FROM Anggota a WHERE a.namaMhs = :namaMhs"),
    @NamedQuery(name = "Anggota.findByAlamat", query = "SELECT a FROM Anggota a WHERE a.alamat = :alamat"),
    @NamedQuery(name = "Anggota.findByTelepon", query = "SELECT a FROM Anggota a WHERE a.telepon = :telepon")})
public class Anggota implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Column(name = "nama_mhs")
    private String namaMhs;
    @Column(name = "alamat")
    private String alamat;
    @Column(name = "telepon")
    private String telepon;

    public Anggota() {
    }

    public Anggota(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamaMhs() {
        return namaMhs;
    }

    public void setNamaMhs(String namaMhs) {
        this.namaMhs = namaMhs;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
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
        if (!(object instanceof Anggota)) {
            return false;
        }
        Anggota other = (Anggota) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UASPBO.Anggota[ id=" + id + " ]";
    }
    
}
