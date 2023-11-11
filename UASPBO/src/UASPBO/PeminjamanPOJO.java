/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UASPBO;

/**
 *
 * @author RIO
 */
public class PeminjamanPOJO {
    private String id_peminjaman;
    private String tanggal_peminjaman;
    private String isbn;
    private String id_anggota;

    /**
     * @return the id_peminjaman
     */
    public String getId_peminjaman() {
        return id_peminjaman;
    }

    /**
     * @param id_peminjaman the id_peminjaman to set
     */
    public void setId_peminjaman(String id_peminjaman) {
        this.id_peminjaman = id_peminjaman;
    }

    /**
     * @return the tanggal_peminjaman
     */
    public String getTanggal_peminjaman() {
        return tanggal_peminjaman;
    }

    /**
     * @param tanggal_peminjaman the tanggal_peminjaman to set
     */
    public void setTanggal_peminjaman(String tanggal_peminjaman) {
        this.tanggal_peminjaman = tanggal_peminjaman;
    }

    /**
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * @return the id_anggota
     */
    public String getId_anggota() {
        return id_anggota;
    }

    /**
     * @param id_anggota the id_anggota to set
     */
    public void setId_anggota(String id_anggota) {
        this.id_anggota = id_anggota;
    }
}
