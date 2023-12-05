/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UASPBO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author RIO
 */
@Entity
@Table(name = "peminjaman")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Peminjaman.findAll", query = "SELECT p FROM Peminjaman p"),
    @NamedQuery(name = "Peminjaman.findByIdPeminjaman", query = "SELECT p FROM Peminjaman p WHERE p.idPeminjaman = :idPeminjaman"),
    @NamedQuery(name = "Peminjaman.findByNamaPeminjam", query = "SELECT p FROM Peminjaman p WHERE p.namaPeminjam = :namaPeminjam"),
    @NamedQuery(name = "Peminjaman.findByAngkatan", query = "SELECT p FROM Peminjaman p WHERE p.angkatan = :angkatan"),
    @NamedQuery(name = "Peminjaman.findByTanggalPinjam", query = "SELECT p FROM Peminjaman p WHERE p.tanggalPinjam = :tanggalPinjam"),
    @NamedQuery(name = "Peminjaman.findByTanggalKembali", query = "SELECT p FROM Peminjaman p WHERE p.tanggalKembali = :tanggalKembali"),
    @NamedQuery(name = "Peminjaman.findByJumlah", query = "SELECT p FROM Peminjaman p WHERE p.jumlah = :jumlah"),
    @NamedQuery(name = "Peminjaman.findByKategori", query = "SELECT p FROM Peminjaman p WHERE p.kategori = :kategori")})
public class Peminjaman implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_peminjaman")
    private String idPeminjaman;
    @Column(name = "nama_peminjam")
    private String namaPeminjam;
    @Column(name = "angkatan")
    private String angkatan;
    @Column(name = "tanggal_pinjam")
    @Temporal(TemporalType.DATE)
    private Date tanggalPinjam;
    @Column(name = "tanggal_kembali")
    @Temporal(TemporalType.DATE)
    private Date tanggalKembali;
    @Column(name = "jumlah")
    private Integer jumlah;
    @Column(name = "kategori")
    private String kategori;
    @JoinColumn(name = "id_peminjam", referencedColumnName = "id_anggota")
    @ManyToOne
    private Anggota idPeminjam;
    @JoinColumn(name = "id_buku", referencedColumnName = "isbn")
    @ManyToOne
    private Buku idBuku;
    @JoinColumn(name = "id_skripsi", referencedColumnName = "nim")
    @ManyToOne
    private Skripsi idSkripsi;

    public Peminjaman() {
    }

    public Peminjaman(String idPeminjaman) {
        this.idPeminjaman = idPeminjaman;
    }

    public String getIdPeminjaman() {
        return idPeminjaman;
    }

    public void setIdPeminjaman(String idPeminjaman) {
        this.idPeminjaman = idPeminjaman;
    }

    public String getNamaPeminjam() {
        return namaPeminjam;
    }

    public void setNamaPeminjam(String namaPeminjam) {
        this.namaPeminjam = namaPeminjam;
    }

    public String getAngkatan() {
        return angkatan;
    }

    public void setAngkatan(String angkatan) {
        this.angkatan = angkatan;
    }

    public Date getTanggalPinjam() {
        return tanggalPinjam;
    }

    public void setTanggalPinjam(Date tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    public Date getTanggalKembali() {
        return tanggalKembali;
    }

    public void setTanggalKembali(Date tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public Anggota getIdPeminjam() {
        return idPeminjam;
    }

    public void setIdPeminjam(Anggota idPeminjam) {
        this.idPeminjam = idPeminjam;
    }

    public Buku getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(Buku idBuku) {
        this.idBuku = idBuku;
    }

    public Skripsi getIdSkripsi() {
        return idSkripsi;
    }

    public void setIdSkripsi(Skripsi idSkripsi) {
        this.idSkripsi = idSkripsi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPeminjaman != null ? idPeminjaman.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Peminjaman)) {
            return false;
        }
        Peminjaman other = (Peminjaman) object;
        if ((this.idPeminjaman == null && other.idPeminjaman != null) || (this.idPeminjaman != null && !this.idPeminjaman.equals(other.idPeminjaman))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UASPBO.Peminjaman[ idPeminjaman=" + idPeminjaman + " ]";
    }
    
}
