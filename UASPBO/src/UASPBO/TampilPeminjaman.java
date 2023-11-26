/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UASPBO;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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
        
        EntityManagerFactory emf = (EntityManagerFactory) Persistence.createEntityManagerFactory("UASPBOPU").createEntityManager();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Buku> cq = cb.createQuery(Buku.class);
        Root<Buku> stud = cq.from(Buku.class);
        cq.select(stud.get("isbn"));
        
        CriteriaQuery<Buku> select = cq.select(stud);
        TypedQuery<Buku> q = em.createQuery(select);
        List<Buku> list = q.getResultList();
        
        for (Buku data : list) {
            Object[] baris = new Object[4];
            baris[0] = data.getIsbn();
            baris[1] = data.getJudulBuku();
            baris[2] = data.getTahunTerbit();
            baris[3] = data.getPenerbit();
            
            model.addRow(baris);
        }
        
        em.getTransaction().commit();
        em.close();
        emf.close();
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
            Object[] baris = new Object[4];
            baris[0] = data.getNim();
            baris[1] = data.getNamaMhs();
            baris[2] = data.getJudulSkripsi();
            baris[3] = data.getTahunSkripsi();
            
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
        cq.select(stud.get("id"));
        
        CriteriaQuery<Anggota> select = cq.select(stud);
        TypedQuery<Anggota> q = em.createQuery(select);
        List<Anggota> list = q.getResultList();
        
        for (Anggota data : list) {
            Object[] baris = new Object[4];
            baris[0] = data.getId();
            baris[1] = data.getNamaMhs();
            baris[2] = data.getAlamat();
            baris[3] = data.getTelepon();
            
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
        cq.select(stud.get("idAnggota"));
        
        CriteriaQuery<Peminjaman> select = cq.select(stud);
        TypedQuery<Peminjaman> q = em.createQuery(select);
        List<Peminjaman> list = q.getResultList();
        
        for (Peminjaman data : list) {
            Object[] baris = new Object[4];
            baris[0] = data.getIdAnggota();
            baris[1] = data.getNamaAnggota();
            baris[2] = data.getIdBuku();
            baris[3] = data.getIdSkripsi();
            baris[4] = data.getTanggalPinjam();
            baris[5] = data.getTanggalKembali();
            
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldIDAnggota = new javax.swing.JTextField();
        jTextFieldISBN = new javax.swing.JTextField();
        jTextFieldNamaAnggota = new javax.swing.JTextField();
        jTextFieldPinjam = new javax.swing.JTextField();
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
        jTextFieldKembali = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableAnggota = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldJudulBuku = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldNIM = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldJudulSkripsi = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableBuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ISBN", "JUDUL BUKU", "TAHUN TERBIT", "PENERBIT"
            }
        ));
        jTableBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableBukuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableBuku);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 180, 470, 190));

        jTableSkripsi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "NIM", "NAMA", "JUDUL SKRIPSI", "TAHUN"
            }
        ));
        jTableSkripsi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableSkripsiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableSkripsi);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 440, 470, 195));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("TABEL ANGGOTA");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 390, -1, 36));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("TABEL BUKU");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 130, -1, 36));
        getContentPane().add(jTextFieldIDAnggota, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 170, -1));
        getContentPane().add(jTextFieldISBN, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 170, -1));
        getContentPane().add(jTextFieldNamaAnggota, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 170, -1));
        getContentPane().add(jTextFieldPinjam, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 170, -1));

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
        getContentPane().add(jButtonSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 660, -1, 50));

        jButtonUbah.setText("UBAH");
        getContentPane().add(jButtonUbah, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 660, -1, 50));

        jButtonHapus.setText("HAPUS");
        getContentPane().add(jButtonHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 660, -1, 50));

        jTablePeminjaman.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTablePeminjaman);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 770, 930, 250));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel7.setText("TABEL PEMINJAMAN");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 720, -1, 36));

        jLabel8.setText("TANGGAL KEMBALI");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, -1, -1));
        getContentPane().add(jTextFieldKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, 170, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setText("TABEL SKRIPSI");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 390, -1, 36));

        jTableAnggota.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID ANGGOTA", "NAMA", "ALAMAT", "NOMOR HP"
            }
        ));
        jTableAnggota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAnggotaMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTableAnggota);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 440, 460, 193));

        jLabel10.setText("JUDUL BUKU");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, -1, -1));
        getContentPane().add(jTextFieldJudulBuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 170, -1));

        jLabel11.setText("ID SKIRPSI (NIM)");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 230, -1, -1));
        getContentPane().add(jTextFieldNIM, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 260, 170, -1));

        jLabel12.setText("JUDUL SKRIPSI");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 310, -1, -1));
        getContentPane().add(jTextFieldJudulSkripsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 340, 170, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableBukuMouseClicked
        // TODO add your handling code here:
        int baris = jTableBuku.rowAtPoint(evt.getPoint());
        
        String isbn = jTableBuku.getValueAt(baris, 0).toString();
        jTextFieldISBN.setText(isbn);        
        
        String judul = jTableBuku.getValueAt(baris, 1).toString();
        jTextFieldJudulBuku.setText(judul);
    }//GEN-LAST:event_jTableBukuMouseClicked

    private void jTableAnggotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAnggotaMouseClicked
        // TODO add your handling code here:
        int baris = jTableAnggota.rowAtPoint(evt.getPoint());
        
        String id = jTableBuku.getValueAt(baris, 0).toString();
        jTextFieldIDAnggota.setText(id);
        
        String nama = jTableBuku.getValueAt(baris, 1).toString();
        jTextFieldNamaAnggota.setText(nama);
    }//GEN-LAST:event_jTableAnggotaMouseClicked

    private void jTableSkripsiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableSkripsiMouseClicked
        // TODO add your handling code here:
        int baris = jTableSkripsi.rowAtPoint(evt.getPoint());
        
        String id = jTableSkripsi.getValueAt(baris, 0).toString();
        jTextFieldNIM.setText(id);
        
        String judul = jTableSkripsi.getValueAt(baris, 1).toString();
        jTextFieldJudulSkripsi.setText(judul);
    }//GEN-LAST:event_jTableSkripsiMouseClicked

    private void jButtonSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSimpanActionPerformed
        // TODO add your handling code here:
        try {
            String id = jTextFieldIDAnggota.getText().trim();
            String nama = jTextFieldNamaAnggota.getText();
            String isbn = jTextFieldISBN.getText();
            String nim = jTextFieldNIM.getText();
            String pinjam = jTextFieldPinjam.getText();
            String kembali = jTextFieldKembali.getText();
            
            EntityManager entityManager = Persistence.createEntityManagerFactory("UASPBOPU").createEntityManager();
            
            entityManager.getTransaction().begin();
            
            Peminjaman p = new Peminjaman();
            
            p.setIdAnggota(id);
            p.setNamaAnggota(nama);
            p.setIdBuku(isbn);
            p.setIdSkripsi(nim);
//            p.setTanggalPinjam(pinjam);
//            p.setTanggalKemballi(kembali);
            
            entityManager.persist(p);
            entityManager.getTransaction().commit();
            
            JOptionPane.showMessageDialog(null, "Simpan Berhasil");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Simpan Gagal");
        }
        
        jTextFieldIDAnggota.setText("");
        jTextFieldNamaAnggota.setText("");
        jTextFieldISBN.setText("");
        jTextFieldNIM.setText("");
        jTextFieldPinjam.setText("");
        jTextFieldKembali.setText("");
        tampilPeminjaman();        
    }//GEN-LAST:event_jButtonSimpanActionPerformed

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
    private javax.swing.JButton jButtonHapus;
    private javax.swing.JButton jButtonSimpan;
    private javax.swing.JButton jButtonUbah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTableAnggota;
    private javax.swing.JTable jTableBuku;
    private javax.swing.JTable jTablePeminjaman;
    private javax.swing.JTable jTableSkripsi;
    private javax.swing.JTextField jTextFieldIDAnggota;
    private javax.swing.JTextField jTextFieldISBN;
    private javax.swing.JTextField jTextFieldJudulBuku;
    private javax.swing.JTextField jTextFieldJudulSkripsi;
    private javax.swing.JTextField jTextFieldKembali;
    private javax.swing.JTextField jTextFieldNIM;
    private javax.swing.JTextField jTextFieldNamaAnggota;
    private javax.swing.JTextField jTextFieldPinjam;
    // End of variables declaration//GEN-END:variables
}
