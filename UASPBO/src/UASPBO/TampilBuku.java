/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UASPBO;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.paint.Color.color;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static jdk.nashorn.internal.objects.Global.print;
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
public class TampilBuku extends javax.swing.JFrame {

    public void peringatan(String pesan) {
        JOptionPane.showMessageDialog(rootPane, pesan);
    }

    ArrayList<Buku> dataBuku;

    private void tampil() {

        DefaultTableModel model = (DefaultTableModel) jTableBuku.getModel();
        model.setRowCount(0);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UASPBOPU");
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
            baris[1] = data.getJudul();
            baris[2] = data.getTahun();
            baris[3] = data.getPenerbit();

            model.addRow(baris);
        }

        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    public TampilBuku() {
//        try {
        dataBuku = new ArrayList<>();
        initComponents();
//            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "11111");
        tampil();
//
//        } catch (SQLException ex) {
//            Logger.getLogger(TampilBuku.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldISBN = new javax.swing.JTextField();
        jTextFieldJudul = new javax.swing.JTextField();
        jTextFieldTahun = new javax.swing.JTextField();
        jTextFieldPenerbit = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableBuku = new javax.swing.JTable();
        jButtonSimpan = new javax.swing.JButton();
        jButtonEdit = new javax.swing.JButton();
        jButtonHapus = new javax.swing.JButton();
        jButtonUpload = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButtonKembali = new javax.swing.JButton();
        jButtonCetak = new javax.swing.JButton();
        jTextFieldCari = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("ISBN");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 70, 30));

        jLabel2.setText("Judul Buku");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 241, -1, 30));

        jLabel3.setText("Tahun Terbit");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, -1, -1));

        jLabel4.setText("Penerbit");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 340, 83, 30));
        jPanel1.add(jTextFieldISBN, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, 230, -1));
        jPanel1.add(jTextFieldJudul, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 240, 230, -1));
        jPanel1.add(jTextFieldTahun, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 230, -1));
        jPanel1.add(jTextFieldPenerbit, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 340, 230, -1));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        jTableBuku.setBackground(new java.awt.Color(242, 242, 242));
        jTableBuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ISBN", "Judul Buku", "Tahun Terbit", "Penerbit"
            }
        ));
        jTableBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableBukuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableBuku);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 90, 630, 528));

        jButtonSimpan.setBackground(new java.awt.Color(242, 242, 242));
        jButtonSimpan.setText("Simpan");
        jButtonSimpan.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButtonSimpanMouseMoved(evt);
            }
        });
        jButtonSimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonSimpanMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonSimpanMouseExited(evt);
            }
        });
        jButtonSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSimpanActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 110, 50));

        jButtonEdit.setBackground(new java.awt.Color(242, 242, 242));
        jButtonEdit.setText("Edit");
        jButtonEdit.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButtonEditMouseMoved(evt);
            }
        });
        jButtonEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonEditMouseExited(evt);
            }
        });
        jButtonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 450, 110, 50));

        jButtonHapus.setBackground(new java.awt.Color(242, 242, 242));
        jButtonHapus.setText("Hapus");
        jButtonHapus.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButtonHapusMouseMoved(evt);
            }
        });
        jButtonHapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonHapusMouseExited(evt);
            }
        });
        jButtonHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHapusActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 450, 110, 50));

        jButtonUpload.setBackground(new java.awt.Color(242, 242, 242));
        jButtonUpload.setText("Upload");
        jButtonUpload.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButtonUploadMouseMoved(evt);
            }
        });
        jButtonUpload.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonUploadMouseExited(evt);
            }
        });
        jButtonUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUploadActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonUpload, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 110, 50));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel5.setText("DATA BUKU PERPUSTAKAAN UINSA");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 6, -1, -1));

        jButtonKembali.setBackground(new java.awt.Color(242, 242, 242));
        jButtonKembali.setText("Kembali");
        jButtonKembali.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButtonKembaliMouseMoved(evt);
            }
        });
        jButtonKembali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonKembaliMouseExited(evt);
            }
        });
        jButtonKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKembaliActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 530, 110, 50));

        jButtonCetak.setBackground(new java.awt.Color(242, 242, 242));
        jButtonCetak.setText("Cetak");
        jButtonCetak.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButtonCetakMouseMoved(evt);
            }
        });
        jButtonCetak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonCetakMouseExited(evt);
            }
        });
        jButtonCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCetakActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonCetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 530, 110, 50));

        jTextFieldCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldCariKeyReleased(evt);
            }
        });
        jPanel1.add(jTextFieldCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 230, -1));

        jLabel6.setText("Cari Buku");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ISBN", "Judul", "Tahun", "Penerbit" }));
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 110, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSimpanActionPerformed

        try {
            String isbn = jTextFieldISBN.getText().trim();
            String judul = jTextFieldJudul.getText();
            String tahun = jTextFieldTahun.getText();
            String penerbit = jTextFieldPenerbit.getText();

            EntityManager entityManager = Persistence.createEntityManagerFactory("UASPBOPU").createEntityManager();

            entityManager.getTransaction().begin();

            Buku b = new Buku();

            b.setIsbn(isbn);
            b.setJudul(judul);
            b.setTahun(tahun);
            b.setPenerbit(penerbit);

            entityManager.persist(b);
            entityManager.getTransaction().commit();

            JOptionPane.showMessageDialog(null, "Simpan Berhasil");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Simpan Gagal");
        }

        jTextFieldISBN.setText("");
        jTextFieldJudul.setText("");
        jTextFieldTahun.setText("");
        jTextFieldPenerbit.setText("");

        tampil();
    }//GEN-LAST:event_jButtonSimpanActionPerformed

    private void jButtonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditActionPerformed

        try {
            String isbn = jTextFieldISBN.getText().trim();
            String judul = jTextFieldJudul.getText();
            String tahun = jTextFieldTahun.getText();
            String penerbit = jTextFieldPenerbit.getText();
            EntityManager entityManager = Persistence.createEntityManagerFactory("UASPBOPU").createEntityManager();

            entityManager.getTransaction().begin();
            Buku b = entityManager.find(Buku.class, isbn);

            b.setIsbn(isbn);
            b.setJudul(judul);
            b.setTahun(tahun);
            b.setPenerbit(penerbit);

            entityManager.persist(b);
            entityManager.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Ubah Berhasil");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ubah Gagal");
        }

        jTextFieldISBN.setText("");
        jTextFieldJudul.setText("");
        jTextFieldTahun.setText("");
        jTextFieldPenerbit.setText("");

        tampil();
    }//GEN-LAST:event_jButtonEditActionPerformed

    private void jButtonHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHapusActionPerformed

        try {

            String isbn = jTextFieldISBN.getText().trim();
            String judul = jTextFieldJudul.getText();
            String tahun = jTextFieldTahun.getText();
            String penerbit = jTextFieldPenerbit.getText();
            EntityManager entityManager = Persistence.createEntityManagerFactory("UASPBOPU").createEntityManager();

            entityManager.getTransaction().begin();
            Buku b = entityManager.find(Buku.class,
                    isbn);

            b.setIsbn(isbn);
            b.setJudul(judul);
            b.setTahun(tahun);
            b.setPenerbit(penerbit);

            entityManager.persist(b);
            entityManager.remove(b);
            entityManager.getTransaction().commit();
            entityManager.close();
            JOptionPane.showMessageDialog(null, "Hapus Berhasil");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Hapus Gagal");
        }
        jTextFieldISBN.setText("");
        jTextFieldJudul.setText("");
        jTextFieldTahun.setText("");
        jTextFieldPenerbit.setText("");

        tampil();
    }//GEN-LAST:event_jButtonHapusActionPerformed

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void jTableBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableBukuMouseClicked
        int baris = jTableBuku.rowAtPoint(evt.getPoint());

        String isbn = jTableBuku.getValueAt(baris, 0).toString();
        jTextFieldISBN.setText(isbn);

        String judul = jTableBuku.getValueAt(baris, 1).toString();
        jTextFieldJudul.setText(judul);

        String tahun = jTableBuku.getValueAt(baris, 2).toString();
        jTextFieldTahun.setText(tahun);

        String penerbit = jTableBuku.getValueAt(baris, 3).toString();
        jTextFieldPenerbit.setText(penerbit);
    }//GEN-LAST:event_jTableBukuMouseClicked

    private void jButtonUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUploadActionPerformed
        // TODO add your handling code here:
        JFileChooser filechooser = new JFileChooser();
        EntityManager entityManager = Persistence.createEntityManagerFactory("UASPBOPU").createEntityManager();

        entityManager.getTransaction().begin();

        int i = filechooser.showOpenDialog(null);
        if (i == JFileChooser.APPROVE_OPTION) {
            File f = filechooser.getSelectedFile();
            String filepath = f.getPath();
            //Parsing CSV Data

            try {

                InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(filepath));
                org.apache.commons.csv.CSVParser csvParser = CSVFormat.DEFAULT.parse(inputStreamReader);
                for (CSVRecord csvRecord : csvParser) {
                    Buku b = new Buku();

                    b.setIsbn(csvRecord.get(0));
                    b.setJudul(csvRecord.get(1));
                    b.setTahun(csvRecord.get(2));
                    b.setPenerbit(csvRecord.get(3));
                    entityManager.persist(b);

                }
                JOptionPane.showMessageDialog(null, "Upload Berhasil");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Gagal Upload");
            }
            jTextFieldISBN.setText("");
            jTextFieldJudul.setText("");
            jTextFieldTahun.setText("");
            jTextFieldPenerbit.setText("");

        }
        entityManager.getTransaction().commit();

        tampil();
    }//GEN-LAST:event_jButtonUploadActionPerformed

    private void jButtonKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKembaliActionPerformed
        // TODO add your handling code here:
        this.hide();
        Menu frameMenu = new Menu();
        frameMenu.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButtonKembaliActionPerformed

    private void jButtonCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCetakActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTableBuku.getModel();
        String jrxmlFile = "src/report/reportBuku.jrxml";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UASPBOPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        if (jComboBox1.getSelectedIndex() == 0) {
            Query query = em.createQuery("SELECT b FROM Buku b WHERE b.isbn LIKE '%" + jTextFieldCari.getText().toLowerCase() + "%'");
            List<Buku> result = query.getResultList();
            for (Buku buku : result) {
                model.addRow(new Object[]{buku.getIsbn(), buku.getJudul(), buku.getTahun(), buku.getPenerbit()});
            }
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(result);

            try {
                // TODO add your handling code here:
                JasperReport jr = JasperCompileManager.compileReport(jrxmlFile);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, dataSource);
                JasperViewer jv = new JasperViewer(jp, false);
                jv.setVisible(true);

            } catch (JRException ex) {
                Logger.getLogger(TampilBuku.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        } else if (jComboBox1.getSelectedIndex() == 1) {
            Query query = em.createQuery("SELECT b FROM Buku b WHERE b.judul LIKE '%" + jTextFieldCari.getText().toLowerCase() + "%'");
            List<Buku> result = query.getResultList();
            for (Buku buku : result) {
                model.addRow(new Object[]{buku.getIsbn(), buku.getJudul(), buku.getTahun(), buku.getPenerbit()});
            }
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(result);

            try {
                // TODO add your handling code here:
                JasperReport jr = JasperCompileManager.compileReport(jrxmlFile);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, dataSource);
                JasperViewer jv = new JasperViewer(jp, false);
                jv.setVisible(true);

            } catch (JRException ex) {
                Logger.getLogger(TampilBuku.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        } else if (jComboBox1.getSelectedIndex() == 2) {
            Query query = em.createQuery("SELECT b FROM Buku b WHERE b.tahun LIKE '%" + jTextFieldCari.getText().toLowerCase() + "%'");
            List<Buku> result = query.getResultList();
            for (Buku buku : result) {
                model.addRow(new Object[]{buku.getIsbn(), buku.getJudul(), buku.getTahun(), buku.getPenerbit()});
            }
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(result);

            try {
                // TODO add your handling code here:
                JasperReport jr = JasperCompileManager.compileReport(jrxmlFile);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, dataSource);
                JasperViewer jv = new JasperViewer(jp, false);
                jv.setVisible(true);

            } catch (JRException ex) {
                Logger.getLogger(TampilBuku.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        } else if (jComboBox1.getSelectedIndex() == 3) {
            Query query = em.createQuery("SELECT b FROM Buku b WHERE b.penerbit LIKE '%" + jTextFieldCari.getText().toLowerCase() + "%'");
            List<Buku> result = query.getResultList();
            for (Buku buku : result) {
                model.addRow(new Object[]{buku.getIsbn(), buku.getJudul(), buku.getTahun(), buku.getPenerbit()});
            }
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(result);

            try {
                // TODO add your handling code here:
                JasperReport jr = JasperCompileManager.compileReport(jrxmlFile);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, dataSource);
                JasperViewer jv = new JasperViewer(jp, false);
                jv.setVisible(true);

            } catch (JRException ex) {
                Logger.getLogger(TampilBuku.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

//        model.getRowCount();
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Buku> cq = cb.createQuery(Buku.class);
//        Root<Buku> stud = cq.from(Buku.class);
//        cq.select(stud);
//
//        Query query = em.createQuery("SELECT b FROM Buku b");
//        List<Buku> result = query.getResultList();
////
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(result);
//
//        try {
//            // TODO add your handling code here:
//            JasperReport jr = JasperCompileManager.compileReport(jrxmlFile);
//            JasperPrint jp = JasperFillManager.fillReport(jr, null, dataSource);
//            JasperViewer jv = new JasperViewer(jp, false);
//            jv.setVisible(true);
//
//        } catch (JRException ex) {
//            Logger.getLogger(TampilBuku.class
//                    .getName()).log(Level.SEVERE, null, ex);
//        }
        
        jTextFieldCari.setText("");
        tampil();
        em.getTransaction().commit();

        emf.close();
    }//GEN-LAST:event_jButtonCetakActionPerformed

    private void jButtonSimpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSimpanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSimpanMouseClicked

    private void jButtonSimpanMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSimpanMouseMoved
        jButtonSimpan.setBackground(Color.GRAY);
    }//GEN-LAST:event_jButtonSimpanMouseMoved

    private void jButtonSimpanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSimpanMouseExited
        jButtonSimpan.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jButtonSimpanMouseExited

    private void jButtonHapusMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonHapusMouseMoved
        jButtonHapus.setBackground(Color.GRAY);
    }//GEN-LAST:event_jButtonHapusMouseMoved

    private void jButtonHapusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonHapusMouseExited
        jButtonHapus.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jButtonHapusMouseExited

    private void jButtonEditMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEditMouseMoved
        jButtonEdit.setBackground(Color.GRAY);
    }//GEN-LAST:event_jButtonEditMouseMoved

    private void jButtonEditMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEditMouseExited
        jButtonEdit.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jButtonEditMouseExited

    private void jButtonUploadMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonUploadMouseMoved
        jButtonUpload.setBackground(Color.GRAY);
    }//GEN-LAST:event_jButtonUploadMouseMoved

    private void jButtonUploadMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonUploadMouseExited
        jButtonUpload.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jButtonUploadMouseExited

    private void jButtonCetakMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCetakMouseMoved
        jButtonCetak.setBackground(Color.GRAY);
    }//GEN-LAST:event_jButtonCetakMouseMoved

    private void jButtonCetakMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCetakMouseExited
        jButtonCetak.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jButtonCetakMouseExited

    private void jButtonKembaliMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonKembaliMouseMoved
        jButtonKembali.setBackground(Color.GRAY);
    }//GEN-LAST:event_jButtonKembaliMouseMoved

    private void jButtonKembaliMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonKembaliMouseExited
        jButtonKembali.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jButtonKembaliMouseExited

    private void jTextFieldCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCariKeyReleased
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTableBuku.getModel();
        model.setRowCount(0);

        EntityManager emf = Persistence.createEntityManagerFactory("UASPBOPU").createEntityManager();

        emf.getTransaction().begin();

        if (jComboBox1.getSelectedIndex() == 0) {
            Query query = emf.createQuery("SELECT b FROM Buku b WHERE b.isbn LIKE '%" + jTextFieldCari.getText().toLowerCase() + "%'");
            List<Buku> result = query.getResultList();
            for (Buku buku : result) {
                model.addRow(new Object[]{buku.getIsbn(), buku.getJudul(), buku.getTahun(), buku.getPenerbit()});
            }
        } else if (jComboBox1.getSelectedIndex() == 1) {
            Query query = emf.createQuery("SELECT b FROM Buku b WHERE b.judul LIKE '%" + jTextFieldCari.getText().toLowerCase() + "%'");
            List<Buku> result = query.getResultList();
            for (Buku buku : result) {
                model.addRow(new Object[]{buku.getIsbn(), buku.getJudul(), buku.getTahun(), buku.getPenerbit()});
            }
        } else if (jComboBox1.getSelectedIndex() == 2) {
            Query query = emf.createQuery("SELECT b FROM Buku b WHERE b.tahun LIKE '%" + jTextFieldCari.getText().toLowerCase() + "%'");
            List<Buku> result = query.getResultList();
            for (Buku buku : result) {
                model.addRow(new Object[]{buku.getIsbn(), buku.getJudul(), buku.getTahun(), buku.getPenerbit()});
            }
        } else if (jComboBox1.getSelectedIndex() == 3) {
            Query query = emf.createQuery("SELECT b FROM Buku b WHERE b.penerbit LIKE '%" + jTextFieldCari.getText().toLowerCase() + "%'");
            List<Buku> result = query.getResultList();
            for (Buku buku : result) {
                model.addRow(new Object[]{buku.getIsbn(), buku.getJudul(), buku.getTahun(), buku.getPenerbit()});
            }
        }

        emf.getTransaction().commit();

        emf.close();
    }//GEN-LAST:event_jTextFieldCariKeyReleased

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
            java.util.logging.Logger.getLogger(TampilBuku.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TampilBuku.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TampilBuku.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TampilBuku.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TampilBuku().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCetak;
    private javax.swing.JButton jButtonEdit;
    private javax.swing.JButton jButtonHapus;
    private javax.swing.JButton jButtonKembali;
    private javax.swing.JButton jButtonSimpan;
    private javax.swing.JButton jButtonUpload;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableBuku;
    private javax.swing.JTextField jTextFieldCari;
    private javax.swing.JTextField jTextFieldISBN;
    private javax.swing.JTextField jTextFieldJudul;
    private javax.swing.JTextField jTextFieldPenerbit;
    private javax.swing.JTextField jTextFieldTahun;
    // End of variables declaration//GEN-END:variables
}
