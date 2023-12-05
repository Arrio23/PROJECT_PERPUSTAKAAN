/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UASPBO;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import static org.eclipse.persistence.expressions.ExpressionMath.mod;
import static org.eclipse.persistence.expressions.ExpressionOperator.mod;
import static sun.util.calendar.CalendarUtils.mod;

/**
 *
 * @author RIO
 */
public class TampilPeminjaman extends javax.swing.JFrame {

    ArrayList<Buku> dataBuku;
    ArrayList<Skripsi> dataSkripsi;
    ArrayList<Anggota> dataAnggota;
    ArrayList<Peminjaman> dataPeminjaman;

    private void tampilBuku() {

        DefaultTableModel model = (DefaultTableModel) jTableBuku.getModel();
        model.setRowCount(0);

        EntityManager em = (EntityManager) Persistence.createEntityManagerFactory("UASPBOPU").createEntityManager();

        em.getTransaction().begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Buku> cq = cb.createQuery(Buku.class);
        Root<Buku> stud = cq.from(Buku.class);
        cq.select(stud.get("isbn"));

        CriteriaQuery<Buku> select = cq.select(stud);
        TypedQuery<Buku> q = em.createQuery(select);
        List<Buku> list = q.getResultList();

        for (Buku data : list) {
            Object[] baris = new Object[5];
            baris[0] = data.getIsbn();
            baris[1] = data.getJudulBuku();
            baris[2] = data.getTahunTerbit();
            baris[3] = data.getPenerbit();
            baris[4] = data.getKategori();

            model.addRow(baris);
        }

        em.getTransaction().commit();
        em.close();
    }

    private void tampilSkripsi() {

        DefaultTableModel model = (DefaultTableModel) jTableSkripsi.getModel();
        model.setRowCount(0);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UASPBOPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Skripsi> cq = cb.createQuery(Skripsi.class);
        Root<Skripsi> stud = cq.from(Skripsi.class);
        cq.select(stud.get("nim"));

        CriteriaQuery<Skripsi> select = cq.select(stud);
        TypedQuery<Skripsi> q = em.createQuery(select);
        List<Skripsi> list = q.getResultList();

        for (Skripsi data : list) {
            Object[] baris = new Object[3];
            baris[0] = data.getNim();
            baris[1] = data.getJudulSkripsi();
            baris[2] = data.getTahunSkripsi();

            model.addRow(baris);
        }

        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    private void tampilAnggota() {

        DefaultTableModel model = (DefaultTableModel) jTableAnggota.getModel();
        model.setRowCount(0);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UASPBOPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Anggota> cq = cb.createQuery(Anggota.class);
        Root<Anggota> stud = cq.from(Anggota.class);
        cq.select(stud.get("idAnggota"));

        CriteriaQuery<Anggota> select = cq.select(stud);
        TypedQuery<Anggota> q = em.createQuery(select);
        List<Anggota> list = q.getResultList();

        for (Anggota data : list) {
            Object[] baris = new Object[4];
            baris[0] = data.getIdAnggota();
            baris[1] = data.getNamaAnggota();
            baris[2] = data.getAlamat();
            baris[3] = data.getAngkatan();

            model.addRow(baris);
        }

        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    private void tampilPeminjaman() {

        DefaultTableModel model = (DefaultTableModel) jTablePeminjaman.getModel();
        model.setRowCount(0);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UASPBOPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Peminjaman> cq = cb.createQuery(Peminjaman.class);
        Root<Peminjaman> stud = cq.from(Peminjaman.class);
        cq.select(stud.get("idPeminjaman"));

        CriteriaQuery<Peminjaman> select = cq.select(stud);
        TypedQuery<Peminjaman> q = em.createQuery(select);
        List<Peminjaman> list = q.getResultList();

        for (Peminjaman data : list) {
            Object[] baris = new Object[10];
            baris[0] = data.getIdPeminjaman();
            baris[1] = data.getIdPeminjam().getIdAnggota();
            baris[2] = data.getNamaPeminjam();
            baris[3] = data.getIdBuku().getIsbn();
            baris[4] = data.getIdSkripsi().getNim();
            baris[5] = data.getTanggalPinjam();
            baris[6] = data.getTanggalKembali();
            baris[7] = data.getJumlah();
            baris[8] = data.getKategori();
            baris[9] = data.getAngkatan();

            model.addRow(baris);
        }

        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    /**
     * Creates new form TampilPeminjaman
     */
    public TampilPeminjaman() {
        dataBuku = new ArrayList<>();
        dataSkripsi = new ArrayList<>();
        dataAnggota = new ArrayList<>();
        dataPeminjaman = new ArrayList<>();

        initComponents();
        tampilBuku();
        tampilSkripsi();
        tampilAnggota();
        tampilPeminjaman();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableBuku = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableSkripsi = new javax.swing.JTable();
        jTextFieldIDAnggota = new javax.swing.JTextField();
        jTextFieldISBN = new javax.swing.JTextField();
        jTextFieldNamaAnggota = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButtonSimpan = new javax.swing.JButton();
        jButtonUbah = new javax.swing.JButton();
        jButtonHapus = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTablePeminjaman = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableAnggota = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldJudulBuku = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldNIM = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldJudulSkripsi = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldIDPeminjaman = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldJumlah = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldKat = new javax.swing.JTextField();
        jButtonCetak = new javax.swing.JButton();
        jButtonKembali = new javax.swing.JButton();
        jTextFieldCari = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextFieldAngkatan = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldCariAnggota = new javax.swing.JTextField();
        jComboBoxAnggota = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldCariBuku = new javax.swing.JTextField();
        jComboBoxBuku = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldCariSkripsi = new javax.swing.JTextField();
        jComboBoxSkripsi = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableBuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ISBN", "JUDUL BUKU", "TAHUN TERBIT", "PENERBIT", "KATEGORI"
            }
        ));
        jTableBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableBukuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableBuku);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 100, 470, 190));

        jTableSkripsi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "NIM", "JUDUL SKRIPSI", "TAHUN"
            }
        ));
        jTableSkripsi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableSkripsiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableSkripsi);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 340, 470, 195));
        getContentPane().add(jTextFieldIDAnggota, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 170, -1));
        getContentPane().add(jTextFieldISBN, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 170, -1));
        getContentPane().add(jTextFieldNamaAnggota, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 170, -1));

        jLabel3.setText("ID ANGGOTA (PEMINJAM)");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, -1));

        jLabel4.setText("NAMA ANGGOTA");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, -1));

        jLabel5.setText("ID BUKU (ISBN)");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));

        jLabel6.setText("TANGGAL PINJAM");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, -1, -1));

        jButtonSimpan.setText("SIMPAN");
        jButtonSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSimpanActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 550, 100, 50));

        jButtonUbah.setText("UBAH");
        jButtonUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUbahActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonUbah, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 620, 100, 50));

        jButtonHapus.setText("HAPUS");
        jButtonHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHapusActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 550, 100, 50));

        jTablePeminjaman.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID PEMINJAMAN", "ID ANGGOTA", "NAMA ANGGOTA", "ID BUKU", "ID SKRIPSI", "TANGGAL PINJAM", "TANGGAL KEMBALI", "JUMLAH", "KATEGORI", "ANGKATAN"
            }
        ));
        jTablePeminjaman.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePeminjamanMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTablePeminjaman);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 710, 1120, 320));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setText("TABEL PEMINJAMAN");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 650, -1, 36));

        jLabel8.setText("TANGGAL KEMBALI");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, -1, -1));

        jTableAnggota.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID ANGGOTA", "NAMA", "ALAMAT", "ANGKATAN"
            }
        ));
        jTableAnggota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAnggotaMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTableAnggota);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 500, 193));

        jLabel10.setText("JUDUL BUKU");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, -1, -1));
        getContentPane().add(jTextFieldJudulBuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 170, -1));

        jLabel11.setText("ID SKIRPSI (NIM)");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 230, -1, -1));
        getContentPane().add(jTextFieldNIM, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 260, 170, -1));

        jLabel12.setText("JUDUL SKRIPSI");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 310, -1, -1));
        getContentPane().add(jTextFieldJudulSkripsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 340, 170, -1));

        jLabel13.setText("ID PEMINJAMAN");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 70, -1, -1));
        getContentPane().add(jTextFieldIDPeminjaman, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, 140, -1));

        jLabel14.setText("JUMLAH");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, -1, -1));
        getContentPane().add(jTextFieldJumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 180, 140, -1));

        jLabel15.setText("KATEGORI BUKU");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 230, -1, -1));
        getContentPane().add(jTextFieldKat, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 260, 140, -1));

        jButtonCetak.setText("CETAK");
        jButtonCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCetakActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonCetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 620, 100, 50));

        jButtonKembali.setText("KEMBALI");
        jButtonKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKembaliActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 550, 100, 50));

        jTextFieldCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldCariKeyReleased(evt);
            }
        });
        getContentPane().add(jTextFieldCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 670, 140, -1));

        jLabel16.setText("Cari");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 670, -1, -1));

        jLabel17.setText("ANGKATAN");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 310, -1, -1));
        getContentPane().add(jTextFieldAngkatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 340, 140, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID PEMINJAMAN", "ID PEMINJAM", "NAMA PEMINJAM", "ID BUKU", "ID SKRIPSI", "BULAN PINJAM", "JUMLAH", "KATEGORI", "ANGKATAN", "BUKU TERBANYAK", "KATEGORI TERBANYAK" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 670, -1, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("TABEL ANGGOTA");

        jTextFieldCariAnggota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldCariAnggotaKeyReleased(evt);
            }
        });

        jComboBoxAnggota.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "NAMA", "ALAMAT", "ANGKATAN" }));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("TABEL BUKU");

        jTextFieldCariBuku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldCariBukuKeyReleased(evt);
            }
        });

        jComboBoxBuku.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ISBN", "JUDUL", "TAHUN", "PENERBIT", "KATEGORI" }));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setText("TABEL SKRIPSI");

        jTextFieldCariSkripsi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldCariSkripsiKeyReleased(evt);
            }
        });

        jComboBoxSkripsi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NIM", "JUDUL", "TAHUN" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextFieldCariAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(616, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel9))
                .addGap(65, 65, 65)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldCariBuku)
                    .addComponent(jTextFieldCariSkripsi, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxSkripsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCariBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCariSkripsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxSkripsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCariAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(604, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1120, 1030));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableBukuMouseClicked
        // TODO add your handling code here:
        int baris = jTableBuku.rowAtPoint(evt.getPoint());

        String isbn = jTableBuku.getValueAt(baris, 0).toString();
        jTextFieldISBN.setText(isbn);

        String judul = jTableBuku.getValueAt(baris, 1).toString();
        jTextFieldJudulBuku.setText(judul);

        String kat = jTableBuku.getValueAt(baris, 4).toString();
        jTextFieldKat.setText(kat);
    }//GEN-LAST:event_jTableBukuMouseClicked

    private void jTableAnggotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAnggotaMouseClicked
        // TODO add your handling code here:
        int baris = jTableAnggota.rowAtPoint(evt.getPoint());

        String id = jTableAnggota.getValueAt(baris, 0).toString();
        jTextFieldIDAnggota.setText(id);

        String nama = jTableAnggota.getValueAt(baris, 1).toString();
        jTextFieldNamaAnggota.setText(nama);

        String angkatan = jTableAnggota.getValueAt(baris, 3).toString();
        jTextFieldAngkatan.setText(angkatan);
    }//GEN-LAST:event_jTableAnggotaMouseClicked

    private void jTableSkripsiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSkripsiMouseClicked
        // TODO add your handling code here:
        int baris = jTableSkripsi.rowAtPoint(evt.getPoint());

        String id = jTableSkripsi.getValueAt(baris, 0).toString();
        jTextFieldNIM.setText(id);

        String judul = jTableSkripsi.getValueAt(baris, 2).toString();
        jTextFieldJudulSkripsi.setText(judul);
    }//GEN-LAST:event_jTableSkripsiMouseClicked

    private void jButtonSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSimpanActionPerformed
        // TODO add your handling code here:
        try {
            String id = jTextFieldIDPeminjaman.getText().trim();
            Anggota idnggota = new Anggota(jTextFieldIDAnggota.getText());
            String nama = jTextFieldNamaAnggota.getText();
            Buku isbn = new Buku(jTextFieldISBN.getText());
            Skripsi nim = new Skripsi(jTextFieldNIM.getText());
            int jumlah = Integer.parseInt(jTextFieldJumlah.getText());
            String kategori = jTextFieldKat.getText();
            String angkatan = jTextFieldAngkatan.getText();
            Date pinjam = jDateChooser2.getDate();
            Date kembali = jDateChooser1.getDate();

//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            Date parsedDateP = dateFormat.parse(jDateChooser2.getDate().toString());
//            Date parseDateK = dateFormat.parse(jDateChooser1.getDate().toString());
//            Timestamp timestamp = new Timestamp(parsedDate.getTime());
            EntityManager entityManager = Persistence.createEntityManagerFactory("UASPBOPU").createEntityManager();

            entityManager.getTransaction().begin();

            Peminjaman p = new Peminjaman();

            p.setIdPeminjaman(id);
            p.setIdPeminjam(idnggota);
            p.setNamaPeminjam(nama);
            p.setIdBuku(isbn);
            p.setIdSkripsi(nim);
            p.setTanggalPinjam(pinjam);
            p.setTanggalKembali(kembali);
            p.setJumlah(jumlah);
            p.setKategori(kategori);
            p.setAngkatan(angkatan);

            entityManager.persist(p);
            entityManager.getTransaction().commit();
            entityManager.close();
            JOptionPane.showMessageDialog(null, "Simpan Berhasil");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Simpan Berhasil");
        }

        jTextFieldIDPeminjaman.setText("");
        jTextFieldIDAnggota.setText("");
        jTextFieldNamaAnggota.setText("");
        jTextFieldISBN.setText("");
        jTextFieldNIM.setText("");
        jDateChooser2.setDateFormatString("");
        jDateChooser1.setDateFormatString("");
        jTextFieldJumlah.setText("");
        jTextFieldKat.setText("");
        jTextFieldAngkatan.setText("");
        jTextFieldJudulBuku.setText("");
        jTextFieldJudulSkripsi.setText("");
        tampilPeminjaman();
    }//GEN-LAST:event_jButtonSimpanActionPerformed

    private void jButtonUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUbahActionPerformed
        // TODO add your handling code here:
        try {
            String id = jTextFieldIDPeminjaman.getText().trim();
            Anggota idnggota = new Anggota(jTextFieldIDAnggota.getText());
            String nama = jTextFieldNamaAnggota.getText();
            Buku isbn = new Buku(jTextFieldISBN.getText());
            Skripsi nim = new Skripsi(jTextFieldNIM.getText());
            int jumlah = Integer.parseInt(jTextFieldJumlah.getText());
            String kategori = jTextFieldKat.getText();
            String angkatan = jTextFieldAngkatan.getText();
            Date pinjam = jDateChooser2.getDate();
            Date kembali = jDateChooser1.getDate();

            EntityManager em = Persistence.createEntityManagerFactory("UASPBOPU").createEntityManager();

            em.getTransaction().begin();

            Peminjaman p = em.find(Peminjaman.class, id);
            p.setIdPeminjaman(id);
            p.setIdPeminjam(idnggota);
            p.setNamaPeminjam(nama);
            p.setIdBuku(isbn);
            p.setIdSkripsi(nim);
            p.setTanggalPinjam(pinjam);
            p.setTanggalKembali(kembali);
            p.setJumlah(jumlah);
            p.setKategori(kategori);
            p.setAngkatan(angkatan);

            em.persist(p);
            em.getTransaction().commit();
            em.close();
            JOptionPane.showMessageDialog(null, "Ubah Berhasil");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ubah Gagal");
        }
        jTextFieldIDPeminjaman.setText("");
        jTextFieldIDAnggota.setText("");
        jTextFieldNamaAnggota.setText("");
        jTextFieldISBN.setText("");
        jTextFieldNIM.setText("");
        jDateChooser2.setDateFormatString("");
        jDateChooser1.setDateFormatString("");
        jTextFieldJumlah.setText("");
        jTextFieldKat.setText("");
        jTextFieldAngkatan.setText("");
        jTextFieldJudulBuku.setText("");
        jTextFieldJudulSkripsi.setText("");
        tampilPeminjaman();
    }//GEN-LAST:event_jButtonUbahActionPerformed

    private void jButtonHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHapusActionPerformed
        // TODO add your handling code here:
        try {
            String id = jTextFieldIDPeminjaman.getText().trim();
            Anggota idnggota = new Anggota(jTextFieldIDAnggota.getText());
            String nama = jTextFieldNamaAnggota.getText();
            Buku isbn = new Buku(jTextFieldISBN.getText());
            Skripsi nim = new Skripsi(jTextFieldNIM.getText());
            int jumlah = Integer.parseInt(jTextFieldJumlah.getText());
            String kategori = jTextFieldKat.getText();
            String angkatan = jTextFieldAngkatan.getText();
            Date pinjam = jDateChooser2.getDate();
            Date kembali = jDateChooser1.getDate();

            EntityManager em = Persistence.createEntityManagerFactory("UASPBOPU").createEntityManager();

            em.getTransaction().begin();

            Peminjaman p = em.find(Peminjaman.class, id);
            p.setIdPeminjaman(id);
            p.setIdPeminjam(idnggota);
            p.setNamaPeminjam(nama);
            p.setIdBuku(isbn);
            p.setIdSkripsi(nim);
            p.setTanggalPinjam(pinjam);
            p.setTanggalKembali(kembali);
            p.setJumlah(jumlah);
            p.setKategori(kategori);
            p.setAngkatan(angkatan);

            em.persist(p);
            em.remove(p);
            em.getTransaction().commit();
            em.close();
            JOptionPane.showMessageDialog(null, "Hapus Berhasil");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hapus Gagal");

        }
        jTextFieldIDPeminjaman.setText("");
        jTextFieldIDAnggota.setText("");
        jTextFieldNamaAnggota.setText("");
        jTextFieldISBN.setText("");
        jTextFieldNIM.setText("");
        jDateChooser2.setDateFormatString("");
        jDateChooser1.setDateFormatString("");
        jTextFieldJumlah.setText("");
        jTextFieldKat.setText("");
        jTextFieldAngkatan.setText("");
        jTextFieldJudulBuku.setText("");
        jTextFieldJudulSkripsi.setText("");
        tampilPeminjaman();
    }//GEN-LAST:event_jButtonHapusActionPerformed

    private void jButtonKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKembaliActionPerformed
        // TODO add your handling code here:
        this.hide();
        Menu frameMenu = new Menu();
        frameMenu.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButtonKembaliActionPerformed

    private void jTablePeminjamanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePeminjamanMouseClicked
        // TODO add your handling code here:
        int baris = jTablePeminjaman.rowAtPoint(evt.getPoint());

        String id = jTablePeminjaman.getValueAt(baris, 0).toString();
        jTextFieldIDPeminjaman.setText(id);

        String idAnggota = jTablePeminjaman.getValueAt(baris, 1).toString();
        jTextFieldIDAnggota.setText(idAnggota);

        String nama = jTablePeminjaman.getValueAt(baris, 2).toString();
        jTextFieldNamaAnggota.setText(nama);

        String isbn = jTablePeminjaman.getValueAt(baris, 3).toString();
        jTextFieldISBN.setText(isbn);

        String nim = jTablePeminjaman.getValueAt(baris, 4).toString();
        jTextFieldNIM.setText(nim);

        Date pinjam = (Date) jTablePeminjaman.getValueAt(baris, 5);
        jDateChooser2.setDate(pinjam);

        Date kembali = (Date) jTablePeminjaman.getValueAt(baris, 6);
        jDateChooser1.setDate(kembali);

        String jumlah = jTablePeminjaman.getValueAt(baris, 7).toString();
        jTextFieldJumlah.setText(jumlah);

        String kategori = jTablePeminjaman.getValueAt(baris, 8).toString();
        jTextFieldKat.setText(kategori);

        String angkatan = jTablePeminjaman.getValueAt(baris, 9).toString();
        jTextFieldAngkatan.setText(angkatan);

    }//GEN-LAST:event_jTablePeminjamanMouseClicked

    private void jTextFieldCariBukuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCariBukuKeyReleased
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTableBuku.getModel();
        model.setRowCount(0);

        EntityManager emf = Persistence.createEntityManagerFactory("UASPBOPU").createEntityManager();

        emf.getTransaction().begin();

        if (jComboBoxBuku.getSelectedIndex() == 0) {
            Query query = emf.createQuery("SELECT b FROM Buku b WHERE b.isbn LIKE '%" + jTextFieldCariBuku.getText().toLowerCase() + "%'");
            List<Buku> result = query.getResultList();
            for (Buku buku : result) {
                model.addRow(new Object[]{buku.getIsbn(), buku.getJudulBuku(), buku.getTahunTerbit(), buku.getPenerbit(), buku.getKategori()});
            }
        } else if (jComboBoxBuku.getSelectedIndex() == 1) {
            Query query = emf.createQuery("SELECT b FROM Buku b WHERE LOWER(b.judulBuku) LIKE '%" + jTextFieldCariBuku.getText().toLowerCase() + "%'");
            List<Buku> result = query.getResultList();
            for (Buku buku : result) {
                model.addRow(new Object[]{buku.getIsbn(), buku.getJudulBuku(), buku.getTahunTerbit(), buku.getPenerbit(), buku.getKategori()});
            }
        } else if (jComboBoxBuku.getSelectedIndex() == 2) {
            Query query = emf.createQuery("SELECT b FROM Buku b WHERE b.tahunTerbit LIKE '%" + jTextFieldCariBuku.getText().toLowerCase() + "%'");
            List<Buku> result = query.getResultList();
            for (Buku buku : result) {
                model.addRow(new Object[]{buku.getIsbn(), buku.getJudulBuku(), buku.getTahunTerbit(), buku.getPenerbit(), buku.getKategori()});
            }
        } else if (jComboBoxBuku.getSelectedIndex() == 3) {
            Query query = emf.createQuery("SELECT b FROM Buku b WHERE LOWER(b.penerbit) LIKE '%" + jTextFieldCariBuku.getText().toLowerCase() + "%'");
            List<Buku> result = query.getResultList();
            for (Buku buku : result) {
                model.addRow(new Object[]{buku.getIsbn(), buku.getJudulBuku(), buku.getTahunTerbit(), buku.getPenerbit(), buku.getKategori()});
            }
        } else if (jComboBoxBuku.getSelectedIndex() == 4) {
            Query query = emf.createQuery("SELECT b FROM Buku b WHERE LOWER(b.kategori) LIKE '%" + jTextFieldCariBuku.getText().toLowerCase() + "%'");
            List<Buku> result = query.getResultList();
            for (Buku buku : result) {
                model.addRow(new Object[]{buku.getIsbn(), buku.getJudulBuku(), buku.getTahunTerbit(), buku.getPenerbit(), buku.getKategori()});
            }
        }

        emf.getTransaction().commit();

        emf.close();
    }//GEN-LAST:event_jTextFieldCariBukuKeyReleased

    private void jTextFieldCariSkripsiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCariSkripsiKeyReleased
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTableSkripsi.getModel();
        model.setRowCount(0);

        EntityManager emf = Persistence.createEntityManagerFactory("UASPBOPU").createEntityManager();

        emf.getTransaction().begin();

        if (jComboBoxSkripsi.getSelectedIndex() == 0) {
            Query query = emf.createQuery("SELECT s FROM Skripsi s WHERE s.nim LIKE '%" + jTextFieldCariSkripsi.getText() + "%'");
            List<Skripsi> result = query.getResultList();
            for (Skripsi skripsi : result) {
                model.addRow(new Object[]{skripsi.getNim(), skripsi.getJudulSkripsi(), skripsi.getTahunSkripsi()});
            }
        } else if (jComboBoxSkripsi.getSelectedIndex() == 1) {
            Query query = emf.createQuery("SELECT s FROM Skripsi s WHERE LOWER(s.judulSkripsi) LIKE '%" + jTextFieldCariSkripsi.getText().toLowerCase() + "%'");
            List<Skripsi> result = query.getResultList();
            for (Skripsi skripsi : result) {
                model.addRow(new Object[]{skripsi.getNim(), skripsi.getJudulSkripsi(), skripsi.getTahunSkripsi()});
            }
        } else if (jComboBoxSkripsi.getSelectedIndex() == 2) {
            Query query = emf.createQuery("SELECT s FROM Skripsi s WHERE s.tahunSkripsi LIKE '%" + jTextFieldCariSkripsi.getText().toLowerCase() + "%'");
            List<Skripsi> result = query.getResultList();
            for (Skripsi skripsi : result) {
                model.addRow(new Object[]{skripsi.getNim(), skripsi.getJudulSkripsi(), skripsi.getTahunSkripsi()});
            }
        }

        emf.getTransaction().commit();

        emf.close();
    }//GEN-LAST:event_jTextFieldCariSkripsiKeyReleased

    private void jTextFieldCariAnggotaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCariAnggotaKeyReleased
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTableAnggota.getModel();
        model.setRowCount(0);

        EntityManager emf = Persistence.createEntityManagerFactory("UASPBOPU").createEntityManager();

        emf.getTransaction().begin();

        if (jComboBoxAnggota.getSelectedIndex() == 0) {
            Query query = emf.createQuery("SELECT a FROM Anggota a WHERE a.idAnggota LIKE '%" + jTextFieldCariAnggota.getText() + "%'");
            List<Anggota> result = query.getResultList();
            for (Anggota anggota : result) {
                model.addRow(new Object[]{anggota.getIdAnggota(), anggota.getNamaAnggota(), anggota.getAlamat(), anggota.getAngkatan()});
            }

        } else if (jComboBoxAnggota.getSelectedIndex() == 1) {
            Query query = emf.createQuery("SELECT a FROM Anggota a WHERE LOWER(a.namaAnggota) LIKE '%" + jTextFieldCariAnggota.getText().toLowerCase() + "%'");
            List<Anggota> result = query.getResultList();
            for (Anggota anggota : result) {
                model.addRow(new Object[]{anggota.getIdAnggota(), anggota.getNamaAnggota(), anggota.getAlamat(), anggota.getAngkatan()});
            }

        } else if (jComboBoxAnggota.getSelectedIndex() == 2) {
            Query query = emf.createQuery("SELECT a FROM Anggota a WHERE LOWER(a.alamat) LIKE '%" + jTextFieldCariAnggota.getText().toLowerCase() + "%'");
            List<Anggota> result = query.getResultList();
            for (Anggota anggota : result) {
                model.addRow(new Object[]{anggota.getIdAnggota(), anggota.getNamaAnggota(), anggota.getAlamat(), anggota.getAngkatan()});
            }

        } else if (jComboBoxAnggota.getSelectedIndex() == 3) {
            Query query = emf.createQuery("SELECT a FROM Anggota a WHERE a.angkatan LIKE '%" + jTextFieldCariAnggota.getText().toLowerCase() + "%'");
            List<Anggota> result = query.getResultList();
            for (Anggota anggota : result) {
                model.addRow(new Object[]{anggota.getIdAnggota(), anggota.getNamaAnggota(), anggota.getAlamat(), anggota.getAngkatan()});
            }

        }

        emf.getTransaction().commit();

        emf.close();
    }//GEN-LAST:event_jTextFieldCariAnggotaKeyReleased

    private void jTextFieldCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCariKeyReleased
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTablePeminjaman.getModel();
        model.setRowCount(0);

        EntityManager em = Persistence.createEntityManagerFactory("UASPBOPU").createEntityManager();

        em.getTransaction().begin();

        if (jComboBox1.getSelectedIndex() == 0) {
            Query query = em.createQuery("SELECT p FROM Peminjaman p WHERE p.idPeminjaman LIKE '%" + jTextFieldCari.getText() + "%'");
            List<Peminjaman> list = query.getResultList();
            for (Peminjaman pinjam : list) {
                model.addRow(new Object[]{pinjam.getIdPeminjaman(), pinjam.getIdPeminjam().getIdAnggota(), pinjam.getNamaPeminjam(), pinjam.getIdBuku().getIsbn(), pinjam.getIdSkripsi().getNim(), pinjam.getTanggalPinjam(), pinjam.getTanggalKembali(), pinjam.getJumlah(), pinjam.getKategori(), pinjam.getAngkatan()});
            }
        } else if (jComboBox1.getSelectedIndex() == 1) {
            Query query = em.createQuery("SELECT p FROM Peminjaman p WHERE p.idPeminjam.idAnggota LIKE '%" + jTextFieldCari.getText() + "%'");
            List<Peminjaman> list = query.getResultList();
            for (Peminjaman pinjam : list) {
                model.addRow(new Object[]{pinjam.getIdPeminjaman(), pinjam.getIdPeminjam().getIdAnggota(), pinjam.getNamaPeminjam(), pinjam.getIdBuku().getIsbn(), pinjam.getIdSkripsi().getNim(), pinjam.getTanggalPinjam(), pinjam.getTanggalKembali(), pinjam.getJumlah(), pinjam.getKategori(), pinjam.getAngkatan()});
            }
        } else if (jComboBox1.getSelectedIndex() == 2) {
            Query query = em.createQuery("SELECT p FROM Peminjaman p WHERE LOWER(p.namaPeminjam) LIKE '%" + jTextFieldCari.getText().toLowerCase() + "%'");
            List<Peminjaman> list = query.getResultList();
            for (Peminjaman pinjam : list) {
                model.addRow(new Object[]{pinjam.getIdPeminjaman(), pinjam.getIdPeminjam().getIdAnggota(), pinjam.getNamaPeminjam(), pinjam.getIdBuku().getIsbn(), pinjam.getIdSkripsi().getNim(), pinjam.getTanggalPinjam(), pinjam.getTanggalKembali(), pinjam.getJumlah(), pinjam.getKategori(), pinjam.getAngkatan()});
            }
        } else if (jComboBox1.getSelectedIndex() == 3) {
            Query query = em.createQuery("SELECT p FROM Peminjaman p WHERE p.idBuku.isbn LIKE '%" + jTextFieldCari.getText() + "%'");
            List<Peminjaman> list = query.getResultList();
            for (Peminjaman pinjam : list) {
                model.addRow(new Object[]{pinjam.getIdPeminjaman(), pinjam.getIdPeminjam().getIdAnggota(), pinjam.getNamaPeminjam(), pinjam.getIdBuku().getIsbn(), pinjam.getIdSkripsi().getNim(), pinjam.getTanggalPinjam(), pinjam.getTanggalKembali(), pinjam.getJumlah(), pinjam.getKategori(), pinjam.getAngkatan()});
            }
        } else if (jComboBox1.getSelectedIndex() == 4) {
            Query query = em.createQuery("SELECT p FROM Peminjaman p WHERE p.idSkripsi.nim LIKE '%" + jTextFieldCari.getText() + "%'");
            List<Peminjaman> list = query.getResultList();
            for (Peminjaman pinjam : list) {
                model.addRow(new Object[]{pinjam.getIdPeminjaman(), pinjam.getIdPeminjam().getIdAnggota(), pinjam.getNamaPeminjam(), pinjam.getIdBuku().getIsbn(), pinjam.getIdSkripsi().getNim(), pinjam.getTanggalPinjam(), pinjam.getTanggalKembali(), pinjam.getJumlah(), pinjam.getKategori(), pinjam.getAngkatan()});
            }
        } else if (jComboBox1.getSelectedIndex() == 5) {
            Query query = em.createQuery("SELECT p FROM Peminjaman p WHERE EXTRACT(MONTH FROM p.tanggalPinjam) =" + jTextFieldCari.getText());
            List<Peminjaman> list = query.getResultList();
            for (Peminjaman pinjam : list) {
                model.addRow(new Object[]{pinjam.getIdPeminjaman(), pinjam.getIdPeminjam().getIdAnggota(), pinjam.getNamaPeminjam(), pinjam.getIdBuku().getIsbn(), pinjam.getIdSkripsi().getNim(), pinjam.getTanggalPinjam(), pinjam.getTanggalKembali(), pinjam.getJumlah(), pinjam.getKategori(), pinjam.getAngkatan()});
            }
        } else if (jComboBox1.getSelectedIndex() == 6) {
            Query query = em.createQuery("SELECT p FROM Peminjaman p WHERE p.jumlah LIKE '%" + jTextFieldCari.getText() + "%'");
            List<Peminjaman> list = query.getResultList();
            for (Peminjaman pinjam : list) {
                model.addRow(new Object[]{pinjam.getIdPeminjaman(), pinjam.getIdPeminjam().getIdAnggota(), pinjam.getNamaPeminjam(), pinjam.getIdBuku().getIsbn(), pinjam.getIdSkripsi().getNim(), pinjam.getTanggalPinjam(), pinjam.getTanggalKembali(), pinjam.getJumlah(), pinjam.getKategori(), pinjam.getAngkatan()});
            }
        } else if (jComboBox1.getSelectedIndex() == 7) {
            Query query = em.createQuery("SELECT p FROM Peminjaman p WHERE LOWER(p.kategori) LIKE '%" + jTextFieldCari.getText().toLowerCase() + "%'");
            List<Peminjaman> list = query.getResultList();
            for (Peminjaman pinjam : list) {
                model.addRow(new Object[]{pinjam.getIdPeminjaman(), pinjam.getIdPeminjam().getIdAnggota(), pinjam.getNamaPeminjam(), pinjam.getIdBuku().getIsbn(), pinjam.getIdSkripsi().getNim(), pinjam.getTanggalPinjam(), pinjam.getTanggalKembali(), pinjam.getJumlah(), pinjam.getKategori(), pinjam.getAngkatan()});
            }
        } else if (jComboBox1.getSelectedIndex() == 8) {
            Query query = em.createQuery("SELECT p FROM Peminjaman p WHERE p.angkatan LIKE '%" + jTextFieldCari.getText() + "%'");
            List<Peminjaman> list = query.getResultList();
            for (Peminjaman pinjam : list) {
                model.addRow(new Object[]{pinjam.getIdPeminjaman(), pinjam.getIdPeminjam().getIdAnggota(), pinjam.getNamaPeminjam(), pinjam.getIdBuku().getIsbn(), pinjam.getIdSkripsi().getNim(), pinjam.getTanggalPinjam(), pinjam.getTanggalKembali(), pinjam.getJumlah(), pinjam.getKategori(), pinjam.getAngkatan()});
            }
        }

        em.getTransaction().commit();

        em.close();
    }//GEN-LAST:event_jTextFieldCariKeyReleased

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTablePeminjaman.getModel();
        model.setRowCount(0);

        EntityManager em = Persistence.createEntityManagerFactory("UASPBOPU").createEntityManager();

        em.getTransaction().begin();

        if (jComboBox1.getSelectedIndex() == 9) {
            Query query = em.createQuery("SELECT p FROM Peminjaman p WHERE p.jumlah = (SELECT MAX(p.jumlah) FROM Peminjaman p)");
            List<Peminjaman> list = query.getResultList();
            for (Peminjaman pinjam : list) {
                model.addRow(new Object[]{pinjam.getIdPeminjaman(), pinjam.getIdPeminjam().getIdAnggota(), pinjam.getNamaPeminjam(), pinjam.getIdBuku().getIsbn(), pinjam.getIdSkripsi().getNim(), pinjam.getTanggalPinjam(), pinjam.getTanggalKembali(), pinjam.getJumlah(), pinjam.getKategori(), pinjam.getAngkatan()});
            }
        } else if (jComboBox1.getSelectedIndex() == 10) {
            Query query = em.createQuery("SELECT p FROM Peminjaman p ORDER BY p.jumlah DESC");
            List<Peminjaman> list = query.getResultList();
            for (Peminjaman pinjam : list) {
                model.addRow(new Object[]{pinjam.getIdPeminjaman(), pinjam.getIdPeminjam().getIdAnggota(), pinjam.getNamaPeminjam(), pinjam.getIdBuku().getIsbn(), pinjam.getIdSkripsi().getNim(), pinjam.getTanggalPinjam(), pinjam.getTanggalKembali(), pinjam.getJumlah(), pinjam.getKategori(), pinjam.getAngkatan()});
            }
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButtonCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCetakActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTablePeminjaman.getModel();
        String searchTerm = jComboBox1.getSelectedItem().toString();
        String selection = jTextFieldCari.getText();
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("searchTerm", searchTerm);
        parameter.put("selection", selection);
        String jrxmlFile = "src/report/reportPeminjaman.jrxml";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UASPBOPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        if (jComboBox1.getSelectedIndex() == 0) {
            Query query = em.createQuery("SELECT p FROM Peminjaman p WHERE p.idPeminjaman LIKE '%" + jTextFieldCari.getText() + "%'");
            List<Peminjaman> list = query.getResultList();
            for (Peminjaman pinjam : list) {
                model.addRow(new Object[]{pinjam.getIdPeminjaman(), pinjam.getIdPeminjam().getIdAnggota(), pinjam.getNamaPeminjam(), pinjam.getIdBuku().getIsbn(), pinjam.getIdSkripsi().getNim(), pinjam.getTanggalPinjam(), pinjam.getTanggalKembali(), pinjam.getJumlah(), pinjam.getKategori(), pinjam.getAngkatan()});
            }
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
            
            try {
                // TODO add your handling code here:
                JasperReport jr = JasperCompileManager.compileReport(jrxmlFile);
                JasperPrint jp = JasperFillManager.fillReport(jr, parameter, dataSource);
                JasperViewer jv = new JasperViewer(jp, false);
                jv.setVisible(true);
                
            } catch (JRException ex) {
                Logger.getLogger(TampilPeminjaman.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if (jComboBox1.getSelectedIndex() == 1) {
            Query query = em.createQuery("SELECT p FROM Peminjaman p WHERE p.idPeminjam.idAnggota LIKE '%" + jTextFieldCari.getText() + "%'");
            List<Peminjaman> list = query.getResultList();
            for (Peminjaman pinjam : list) {
                model.addRow(new Object[]{pinjam.getIdPeminjaman(), pinjam.getIdPeminjam().getIdAnggota(), pinjam.getNamaPeminjam(), pinjam.getIdBuku().getIsbn(), pinjam.getIdSkripsi().getNim(), pinjam.getTanggalPinjam(), pinjam.getTanggalKembali(), pinjam.getJumlah(), pinjam.getKategori(), pinjam.getAngkatan()});
            }
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
            
            try {
                // TODO add your handling code here:
                JasperReport jr = JasperCompileManager.compileReport(jrxmlFile);
                JasperPrint jp = JasperFillManager.fillReport(jr, parameter, dataSource);
                JasperViewer jv = new JasperViewer(jp, false);
                jv.setVisible(true);
                
            } catch (JRException ex) {
                Logger.getLogger(TampilPeminjaman.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if (jComboBox1.getSelectedIndex() == 2) {
            Query query = em.createQuery("SELECT p FROM Peminjaman p WHERE LOWER(p.namaPeminjam) LIKE '%" + jTextFieldCari.getText().toLowerCase() + "%'");
            List<Peminjaman> list = query.getResultList();
            for (Peminjaman pinjam : list) {
                model.addRow(new Object[]{pinjam.getIdPeminjaman(), pinjam.getIdPeminjam().getIdAnggota(), pinjam.getNamaPeminjam(), pinjam.getIdBuku().getIsbn(), pinjam.getIdSkripsi().getNim(), pinjam.getTanggalPinjam(), pinjam.getTanggalKembali(), pinjam.getJumlah(), pinjam.getKategori(), pinjam.getAngkatan()});
            }
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
            
            try {
                // TODO add your handling code here:
                JasperReport jr = JasperCompileManager.compileReport(jrxmlFile);
                JasperPrint jp = JasperFillManager.fillReport(jr, parameter, dataSource);
                JasperViewer jv = new JasperViewer(jp, false);
                jv.setVisible(true);
                
            } catch (JRException ex) {
                Logger.getLogger(TampilPeminjaman.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if (jComboBox1.getSelectedIndex() == 3) {
            Query query = em.createQuery("SELECT p FROM Peminjaman p WHERE p.idBuku.isbn LIKE '%" + jTextFieldCari.getText() + "%'");
            List<Peminjaman> list = query.getResultList();
            for (Peminjaman pinjam : list) {
                model.addRow(new Object[]{pinjam.getIdPeminjaman(), pinjam.getIdPeminjam().getIdAnggota(), pinjam.getNamaPeminjam(), pinjam.getIdBuku().getIsbn(), pinjam.getIdSkripsi().getNim(), pinjam.getTanggalPinjam(), pinjam.getTanggalKembali(), pinjam.getJumlah(), pinjam.getKategori(), pinjam.getAngkatan()});
            }
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
            
            try {
                // TODO add your handling code here:
                JasperReport jr = JasperCompileManager.compileReport(jrxmlFile);
                JasperPrint jp = JasperFillManager.fillReport(jr, parameter, dataSource);
                JasperViewer jv = new JasperViewer(jp, false);
                jv.setVisible(true);
                
            } catch (JRException ex) {
                Logger.getLogger(TampilPeminjaman.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if (jComboBox1.getSelectedIndex() == 4) {
            Query query = em.createQuery("SELECT p FROM Peminjaman p WHERE p.idSkripsi.nim LIKE '%" + jTextFieldCari.getText() + "%'");
            List<Peminjaman> list = query.getResultList();
            for (Peminjaman pinjam : list) {
                model.addRow(new Object[]{pinjam.getIdPeminjaman(), pinjam.getIdPeminjam().getIdAnggota(), pinjam.getNamaPeminjam(), pinjam.getIdBuku().getIsbn(), pinjam.getIdSkripsi().getNim(), pinjam.getTanggalPinjam(), pinjam.getTanggalKembali(), pinjam.getJumlah(), pinjam.getKategori(), pinjam.getAngkatan()});
            }
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
            
            try {
                // TODO add your handling code here:
                JasperReport jr = JasperCompileManager.compileReport(jrxmlFile);
                JasperPrint jp = JasperFillManager.fillReport(jr, parameter, dataSource);
                JasperViewer jv = new JasperViewer(jp, false);
                jv.setVisible(true);
                
            } catch (JRException ex) {
                Logger.getLogger(TampilPeminjaman.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if (jComboBox1.getSelectedIndex() == 5) {
            Query query = em.createQuery("SELECT p FROM Peminjaman p WHERE EXTRACT(MONTH FROM p.tanggalPinjam) =" + jTextFieldCari.getText());
            List<Peminjaman> list = query.getResultList();
            for (Peminjaman pinjam : list) {
                model.addRow(new Object[]{pinjam.getIdPeminjaman(), pinjam.getIdPeminjam().getIdAnggota(), pinjam.getNamaPeminjam(), pinjam.getIdBuku().getIsbn(), pinjam.getIdSkripsi().getNim(), pinjam.getTanggalPinjam(), pinjam.getTanggalKembali(), pinjam.getJumlah(), pinjam.getKategori(), pinjam.getAngkatan()});
            }
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
            
            try {
                // TODO add your handling code here:
                JasperReport jr = JasperCompileManager.compileReport(jrxmlFile);
                JasperPrint jp = JasperFillManager.fillReport(jr, parameter, dataSource);
                JasperViewer jv = new JasperViewer(jp, false);
                jv.setVisible(true);
                
            } catch (JRException ex) {
                Logger.getLogger(TampilPeminjaman.class
                        .getName()).log(Level.SEVERE, null, ex);
            } 
            
        } else if (jComboBox1.getSelectedIndex() == 6) {
            Query query = em.createQuery("SELECT p FROM Peminjaman p WHERE p.jumlah LIKE '%" + jTextFieldCari.getText() + "%'");
            List<Peminjaman> list = query.getResultList();
            for (Peminjaman pinjam : list) {
                model.addRow(new Object[]{pinjam.getIdPeminjaman(), pinjam.getIdPeminjam().getIdAnggota(), pinjam.getNamaPeminjam(), pinjam.getIdBuku().getIsbn(), pinjam.getIdSkripsi().getNim(), pinjam.getTanggalPinjam(), pinjam.getTanggalKembali(), pinjam.getJumlah(), pinjam.getKategori(), pinjam.getAngkatan()});
            }
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
            
            try {
                // TODO add your handling code here:
                JasperReport jr = JasperCompileManager.compileReport(jrxmlFile);
                JasperPrint jp = JasperFillManager.fillReport(jr, parameter, dataSource);
                JasperViewer jv = new JasperViewer(jp, false);
                jv.setVisible(true);
                
            } catch (JRException ex) {
                Logger.getLogger(TampilPeminjaman.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if (jComboBox1.getSelectedIndex() == 7) {
            Query query = em.createQuery("SELECT p FROM Peminjaman p WHERE LOWER(p.kategori) LIKE '%" + jTextFieldCari.getText().toLowerCase() + "%'");
            List<Peminjaman> list = query.getResultList();
            for (Peminjaman pinjam : list) {
                model.addRow(new Object[]{pinjam.getIdPeminjaman(), pinjam.getIdPeminjam().getIdAnggota(), pinjam.getNamaPeminjam(), pinjam.getIdBuku().getIsbn(), pinjam.getIdSkripsi().getNim(), pinjam.getTanggalPinjam(), pinjam.getTanggalKembali(), pinjam.getJumlah(), pinjam.getKategori(), pinjam.getAngkatan()});
            }
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
            
            try {
                // TODO add your handling code here:
                JasperReport jr = JasperCompileManager.compileReport(jrxmlFile);
                JasperPrint jp = JasperFillManager.fillReport(jr, parameter, dataSource);
                JasperViewer jv = new JasperViewer(jp, false);
                jv.setVisible(true);
                
            } catch (JRException ex) {
                Logger.getLogger(TampilPeminjaman.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if (jComboBox1.getSelectedIndex() == 8) {
            Query query = em.createQuery("SELECT p FROM Peminjaman p WHERE p.angkatan LIKE '%" + jTextFieldCari.getText() + "%'");
            List<Peminjaman> list = query.getResultList();
            for (Peminjaman pinjam : list) {
                model.addRow(new Object[]{pinjam.getIdPeminjaman(), pinjam.getIdPeminjam().getIdAnggota(), pinjam.getNamaPeminjam(), pinjam.getIdBuku().getIsbn(), pinjam.getIdSkripsi().getNim(), pinjam.getTanggalPinjam(), pinjam.getTanggalKembali(), pinjam.getJumlah(), pinjam.getKategori(), pinjam.getAngkatan()});
            }
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
            
            try {
                // TODO add your handling code here:
                JasperReport jr = JasperCompileManager.compileReport(jrxmlFile);
                JasperPrint jp = JasperFillManager.fillReport(jr, parameter, dataSource);
                JasperViewer jv = new JasperViewer(jp, false);
                jv.setVisible(true);
                
            } catch (JRException ex) {
                Logger.getLogger(TampilPeminjaman.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else if (jComboBox1.getSelectedIndex() == 9) {
            Query query = em.createQuery("SELECT p FROM Peminjaman p WHERE p.jumlah = (SELECT MAX(p.jumlah) FROM Peminjaman p)");
            List<Peminjaman> list = query.getResultList();
            for (Peminjaman pinjam : list) {
                model.addRow(new Object[]{pinjam.getIdPeminjaman(), pinjam.getIdPeminjam().getIdAnggota(), pinjam.getNamaPeminjam(), pinjam.getIdBuku().getIsbn(), pinjam.getIdSkripsi().getNim(), pinjam.getTanggalPinjam(), pinjam.getTanggalKembali(), pinjam.getJumlah(), pinjam.getKategori(), pinjam.getAngkatan()});
            }
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
            
            try {
                // TODO add your handling code here:
                JasperReport jr = JasperCompileManager.compileReport(jrxmlFile);
                JasperPrint jp = JasperFillManager.fillReport(jr, parameter, dataSource);
                JasperViewer jv = new JasperViewer(jp, false);
                jv.setVisible(true);
                
            } catch (JRException ex) {
                Logger.getLogger(TampilPeminjaman.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if (jComboBox1.getSelectedIndex() == 10) {
            Query query = em.createQuery("SELECT p FROM Peminjaman p ORDER BY p.jumlah DESC");
            List<Peminjaman> list = query.getResultList();
            for (Peminjaman pinjam : list) {
                model.addRow(new Object[]{pinjam.getIdPeminjaman(), pinjam.getIdPeminjam().getIdAnggota(), pinjam.getNamaPeminjam(), pinjam.getIdBuku().getIsbn(), pinjam.getIdSkripsi().getNim(), pinjam.getTanggalPinjam(), pinjam.getTanggalKembali(), pinjam.getJumlah(), pinjam.getKategori(), pinjam.getAngkatan()});
            }
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
            
            try {
                // TODO add your handling code here:
                JasperReport jr = JasperCompileManager.compileReport(jrxmlFile);
                JasperPrint jp = JasperFillManager.fillReport(jr, parameter, dataSource);
                JasperViewer jv = new JasperViewer(jp, false);
                jv.setVisible(true);
                
            } catch (JRException ex) {
                Logger.getLogger(TampilPeminjaman.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        em.close();
        em.getTransaction().commit();
        tampilPeminjaman();
    }//GEN-LAST:event_jButtonCetakActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TampilPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TampilPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TampilPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TampilPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TampilPeminjaman().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCetak;
    private javax.swing.JButton jButtonHapus;
    private javax.swing.JButton jButtonKembali;
    private javax.swing.JButton jButtonSimpan;
    private javax.swing.JButton jButtonUbah;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBoxAnggota;
    private javax.swing.JComboBox<String> jComboBoxBuku;
    private javax.swing.JComboBox<String> jComboBoxSkripsi;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTableAnggota;
    private javax.swing.JTable jTableBuku;
    private javax.swing.JTable jTablePeminjaman;
    private javax.swing.JTable jTableSkripsi;
    private javax.swing.JTextField jTextFieldAngkatan;
    private javax.swing.JTextField jTextFieldCari;
    private javax.swing.JTextField jTextFieldCariAnggota;
    private javax.swing.JTextField jTextFieldCariBuku;
    private javax.swing.JTextField jTextFieldCariSkripsi;
    private javax.swing.JTextField jTextFieldIDAnggota;
    private javax.swing.JTextField jTextFieldIDPeminjaman;
    private javax.swing.JTextField jTextFieldISBN;
    private javax.swing.JTextField jTextFieldJudulBuku;
    private javax.swing.JTextField jTextFieldJudulSkripsi;
    private javax.swing.JTextField jTextFieldJumlah;
    private javax.swing.JTextField jTextFieldKat;
    private javax.swing.JTextField jTextFieldNIM;
    private javax.swing.JTextField jTextFieldNamaAnggota;
    // End of variables declaration//GEN-END:variables
}
