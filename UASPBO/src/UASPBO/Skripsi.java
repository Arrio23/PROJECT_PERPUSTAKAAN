/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UASPBO;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author RIO
 */
@Entity
@Table(name = "skripsi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Skripsi.findAll", query = "SELECT s FROM Skripsi s"),
    @NamedQuery(name = "Skripsi.findByNim", query = "SELECT s FROM Skripsi s WHERE s.nim = :nim"),
    @NamedQuery(name = "Skripsi.findByJudulSkripsi", query = "SELECT s FROM Skripsi s WHERE s.judulSkripsi = :judulSkripsi"),
    @NamedQuery(name = "Skripsi.findByTahunSkripsi", query = "SELECT s FROM Skripsi s WHERE s.tahunSkripsi = :tahunSkripsi")})
public class Skripsi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nim")
    private String nim;
    @Column(name = "judul_skripsi")
    private String judulSkripsi;
    @Column(name = "tahun_skripsi")
    private String tahunSkripsi;
    @OneToMany(mappedBy = "idSkripsi")
    private Collection<Peminjaman> peminjamanCollection;

    public Skripsi() {
    }

    public Skripsi(String nim) {
        this.nim = nim;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getJudulSkripsi() {
        return judulSkripsi;
    }

    public void setJudulSkripsi(String judulSkripsi) {
        this.judulSkripsi = judulSkripsi;
    }

    public String getTahunSkripsi() {
        return tahunSkripsi;
    }

    public void setTahunSkripsi(String tahunSkripsi) {
        this.tahunSkripsi = tahunSkripsi;
    }

    @XmlTransient
    public Collection<Peminjaman> getPeminjamanCollection() {
        return peminjamanCollection;
    }

    public void setPeminjamanCollection(Collection<Peminjaman> peminjamanCollection) {
        this.peminjamanCollection = peminjamanCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nim != null ? nim.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Skripsi)) {
            return false;
        }
        Skripsi other = (Skripsi) object;
        if ((this.nim == null && other.nim != null) || (this.nim != null && !this.nim.equals(other.nim))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UASPBO.Skripsi[ nim=" + nim + " ]";
    }
    
}
