/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lembur;

import java.awt.HeadlessException;
import java.util.List;
import javax.swing.JOptionPane;
import karyawan.KaryawanController;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import karyawan.Karyawan;
import utils.Utils;

/**
 *
 * @author Farhan Fadila
 */
public class FormLembur extends javax.swing.JFrame {
    private final KaryawanController karyawanController = new KaryawanController();
    private final LemburController lemburController = new LemburController();
    private final Utils utils = new Utils();
    private DefaultTableModel model;
    /**
     * Creates new form FormLembur
     */
    public FormLembur() {
        initComponents();
        initColumnTabel();
        initRowTabel();
        txt_id.setEditable(false);
        txt_nama.setEditable(false);
    }
    
    private void initColumnTabel() {
        model = new DefaultTableModel();
        
        tbl_keterlambatan.setModel(model);
        model.addColumn("ID");
        model.addColumn("Nama Karyawan");
        model.addColumn("Jam");
        model.addColumn("Tanggal");
        model.addColumn("Keterangan");
    }
    
      private void initRowTabel() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            
            List<Lembur> lembur =  lemburController.dataLembur();
            
            lembur.stream().map((item) -> {
                Object[] obj = new Object[5];
                Karyawan karyawan = karyawanController.detailKaryawan(item.idKaryawan);
                obj[0] = item.id;
                obj[1] = karyawan.nama;
                obj[2] = item.jam;
                obj[3] = utils.dMY(utils.toDate(item.tanggal));
                obj[4] = item.keterangan;
               return obj;
           }).forEachOrdered((obj) -> { 
               model.addRow(obj);
           });
           
       } catch(Exception ex) {
           System.out.println("initRowTabel ERROR" + ex);
       }
     }
     
    
    private void resetForm() {
         txt_id.setText("");
         txt_id_karyawan.setText("");
         txt_nama.setText("");
         spn_jam.setValue(1);
         txt_keterangan.setText("");
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        btn_tambah = new javax.swing.JButton();
        btn_cari = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_keterlambatan = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        spn_jam = new javax.swing.JSpinner();
        txt_id_karyawan = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_keterangan = new javax.swing.JTextArea();
        btn_reset = new javax.swing.JButton();
        cal_tanggal = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btn_laporan = new javax.swing.JButton();
        txt_id = new javax.swing.JTextField();
        btn_hapus = new javax.swing.JButton();
        txt_nama = new javax.swing.JTextField();
        btn_edit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel3.setText("Nama");

        btn_tambah.setBackground(new java.awt.Color(0, 204, 0));
        btn_tambah.setText("Tambah");
        btn_tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_tambahMousePressed(evt);
            }
        });

        btn_cari.setText("Cari");
        btn_cari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_cariMousePressed(evt);
            }
        });

        jLabel7.setText("Tanggal");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Form Lembur");

        tbl_keterlambatan.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_keterlambatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_keterlambatanMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_keterlambatan);

        jLabel4.setText("Lembur");

        spn_jam.setModel(new javax.swing.SpinnerNumberModel(1, 1, 10, 1));

        jLabel5.setText("jam");

        jLabel9.setText("ID Karyawan");

        txt_keterangan.setColumns(20);
        txt_keterangan.setRows(5);
        jScrollPane1.setViewportView(txt_keterangan);

        btn_reset.setBackground(new java.awt.Color(0, 204, 204));
        btn_reset.setText("Reset");
        btn_reset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_resetMousePressed(evt);
            }
        });

        jLabel2.setText("ID");

        jLabel6.setText("Keterangan");

        btn_laporan.setBackground(new java.awt.Color(255, 153, 51));
        btn_laporan.setText("Laporan");
        btn_laporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_laporanMousePressed(evt);
            }
        });

        btn_hapus.setBackground(new java.awt.Color(255, 51, 51));
        btn_hapus.setText("Hapus");
        btn_hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_hapusMousePressed(evt);
            }
        });

        btn_edit.setBackground(new java.awt.Color(255, 204, 51));
        btn_edit.setText("Edit");
        btn_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_editMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel6))
                                .addGap(52, 52, 52)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txt_id_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(cal_tanggal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txt_nama, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(spn_jam, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel5))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_tambah)
                                .addGap(18, 18, 18)
                                .addComponent(btn_edit)
                                .addGap(18, 18, 18)
                                .addComponent(btn_hapus)
                                .addGap(18, 18, 18)
                                .addComponent(btn_reset)
                                .addGap(18, 18, 18)
                                .addComponent(btn_laporan))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(249, 249, 249)
                        .addComponent(jLabel1)))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel1)
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_id_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cari))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(spn_jam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cal_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tambah)
                    .addComponent(btn_hapus)
                    .addComponent(btn_edit)
                    .addComponent(btn_reset)
                    .addComponent(btn_laporan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_tambahMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tambahMousePressed
      try {
            String idKaryawan = txt_id_karyawan.getText();
            String namaKaryawan = txt_nama.getText();
            int jam = Integer.parseInt(spn_jam.getModel().getValue().toString());
            long tanggal = utils.toEpoch(cal_tanggal.getDate());
            String keterangan = txt_keterangan.getText();
            
            if(!(idKaryawan.isEmpty() || namaKaryawan.isEmpty() || tanggal < 1)) {
                boolean tambah = lemburController.tambah(idKaryawan, jam, tanggal, keterangan);
                
                if(tambah) {
                    JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!");
                    initRowTabel();
                    resetForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Gagal menambah data");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Mohon isi semua data terlebih dahulu");
            }
        } catch(HeadlessException | NumberFormatException ex) {
            System.out.println("Buton Tambah Exception => " + ex);
        }
    }//GEN-LAST:event_btn_tambahMousePressed

    private void btn_cariMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cariMousePressed
        try {
          String id = txt_id_karyawan.getText();
          if(!id.isEmpty()) {
            Karyawan karyawan = karyawanController.detailKaryawan(id);
            txt_nama.setText(karyawan.nama);
          } else {
            JOptionPane.showMessageDialog(this, "Mohon isi ID karyawan terlebih dahulu!");
          }
        } catch(HeadlessException ex) {
          System.out.println(ex);
        }
    }//GEN-LAST:event_btn_cariMousePressed

    private void tbl_keterlambatanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_keterlambatanMousePressed
         try {
            int index = tbl_keterlambatan.getSelectedRow();
            TableModel mdl = tbl_keterlambatan.getModel();
            String id = mdl.getValueAt(index, 0).toString();
            Lembur keterlambatan = lemburController.detailLembur(id);
            Karyawan karyawan = karyawanController.detailKaryawan(keterlambatan.idKaryawan);
            
            txt_id.setText(id);
            txt_id_karyawan.setText(karyawan.id);
            txt_nama.setText(karyawan.nama);
            spn_jam.setValue(keterlambatan.jam);
            cal_tanggal.setDate(utils.toDate(keterlambatan.tanggal));
            txt_keterangan.setText(keterlambatan.keterangan);
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_tbl_keterlambatanMousePressed

    private void btn_resetMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_resetMousePressed
        resetForm();
    }//GEN-LAST:event_btn_resetMousePressed

    private void btn_laporanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_laporanMousePressed
        utils.bukaLaporan("laporan_lembur", lemburController.con);
    }//GEN-LAST:event_btn_laporanMousePressed

    private void btn_hapusMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapusMousePressed
        try {
            String id = txt_id.getText();
            
            lemburController.hapus(id);
            resetForm();
            initRowTabel();
        } catch(Exception ex) {
            System.out.println("Buton Hapus Exception => " + ex);
        }
    }//GEN-LAST:event_btn_hapusMousePressed

    private void btn_editMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editMousePressed
       try {
            String id = txt_id.getText();
            String idKaryawan = txt_id_karyawan.getText();
            String namaKaryawan = txt_nama.getText();
            int jam = Integer.parseInt(spn_jam.getModel().getValue().toString());
            long tanggal = utils.toEpoch(cal_tanggal.getDate());
            String keterangan = txt_keterangan.getText();
            
            if(!(id.isEmpty() || idKaryawan.isEmpty() || namaKaryawan.isEmpty() || tanggal < 1)) {
                boolean tambah = lemburController.edit(id, idKaryawan, jam, tanggal, keterangan);
                
                if(tambah) {
                    JOptionPane.showMessageDialog(this, "Data berhasil diedit!");
                    initRowTabel();
                    resetForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Gagal mengedit data");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Mohon isi semua data terlebih dahulu");
            }
        } catch(HeadlessException | NumberFormatException ex) {
            System.out.println("Buton Edit Exception => " + ex);
        }
    }//GEN-LAST:event_btn_editMousePressed

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
            java.util.logging.Logger.getLogger(FormLembur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormLembur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormLembur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormLembur.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormLembur().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_laporan;
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_tambah;
    private com.toedter.calendar.JDateChooser cal_tanggal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner spn_jam;
    private javax.swing.JTable tbl_keterlambatan;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_id_karyawan;
    private javax.swing.JTextArea txt_keterangan;
    private javax.swing.JTextField txt_nama;
    // End of variables declaration//GEN-END:variables
}
