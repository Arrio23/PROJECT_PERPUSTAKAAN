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
@Table(name = "buku")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Buku.findAll", query = "SELECT b FROM Buku b"),
    @NamedQuery(name = "Buku.findByIsbn", query = "SELECT b FROM Buku b WHERE b.isbn = :isbn"),
    @NamedQuery(name = "Buku.findByJudulBuku", query = "SELECT b FROM Buku b WHERE b.judulBuku = :judulBuku"),
    @NamedQuery(name = "Buku.findByTahunTerbit", query = "SELECT b FROM Buku b WHERE b.tahunTerbit = :tahunTerbit"),
    @NamedQuery(name = "Buku.findByPenerbit", query = "SELECT b FROM Buku b WHERE b.penerbit = :penerbit"),
    @NamedQuery(name = "Buku.findByKategori", query = "SELECT b FROM Buku b WHERE b.kategori = :kategori")})
public class Buku implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "judul_buku")
    private String judulBuku;
    @Column(name = "tahun_terbit")
    private String tahunTerbit;
    @Column(name = "penerbit")
    private String penerbit;
    @Column(name = "kategori")
    private String kategori;
    @OneToMany(mappedBy = "idBuku")
    private Collection<Peminjaman> peminjamanCollection;

    public Buku() {
    }

    public Buku(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getJudulBuku() {
        return judulBuku;
    }

    public void setJudulBuku(String judulBuku) {
        this.judulBuku = judulBuku;
    }

    public String getTahunTerbit() {
        return tahunTerbit;
    }

    public void setTahunTerbit(String tahunTerbit) {
        this.tahunTerbit = tahunTerbit;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
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
        hash += (isbn != null ? isbn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Buku)) {
            return false;
        }
        Buku other = (Buku) object;
        if ((this.isbn == null && other.isbn != null) || (this.isbn != null && !this.isbn.equals(other.isbn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UASPBO.Buku[ isbn=" + isbn + " ]";
    }
    
}
