/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karyawan;

import jabatan.Jabatan;
import jabatan.JabatanController;
import java.awt.HeadlessException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import utils.Utils;

/**
 *
 * @author Farhan Fadila
 */
public class FormKaryawan extends javax.swing.JFrame {
    private final JabatanController jabatanController = new JabatanController();
    private final KaryawanController karyawanController = new KaryawanController();
    private final Utils utils = new Utils();
    private DefaultTableModel model;
    /**
     * Creates new form FormKaryawan
     */
    public FormKaryawan() {
        initComponents();
        initcmbJabatan();
        initColumnTabel();
        initRowTabel();
    }
    
    
    private void initcmbJabatan() {
        try {
           List<Jabatan> jabatan = jabatanController.dataJabatan(true);
            
           jabatan.forEach((item) -> {
               cmb_jabatan.addItem(item.namaJabatan);
            });
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
     private void initColumnTabel() {
        model = new DefaultTableModel ();
        
        tbl_karyawan.setModel(model);
        model.addColumn("ID");
        model.addColumn("Nama");
        model.addColumn("Jabatan");
        model.addColumn("Alamat");
    }
    
    private void initRowTabel() {
       model.getDataVector().removeAllElements();
       model.fireTableDataChanged();
       try {
            List<Karyawan> karyawan = karyawanController.dataKaryawan(false);
            
            
            karyawan.stream().map((item) -> {
                Object[] obj = new Object[4];
                Jabatan jabatan = jabatanController.detailJabatan(item.idJabatan);
                obj[0] = item.id;
                obj[1] = item.nama;
                obj[2] = jabatan.namaJabatan;
                obj[3] = item.alamat;
               return obj;
           }).forEachOrdered((obj) -> { 
               model.addRow(obj);
           });
           
       } catch(Exception ex) {
           System.out.println("initRowTabel ERROR" + ex);
       }
    }
    
    
    private void resetForm() {
        txt_id.setEditable(true);
        txt_id.setText("");
        txt_nama.setText("");
        txt_alamat.setText("");
        cal_lahir.setDate(utils.toDate(960809960));
        cal_masuk.setDate(utils.toDate(utils.epochTimeNow()));
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        kelamin = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        txt_nama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_alamat = new javax.swing.JTextArea();
        cmb_agama = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmb_jabatan = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_karyawan = new javax.swing.JTable();
        btn_hapus = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_tambah = new javax.swing.JButton();
        cal_lahir = new com.toedter.calendar.JDateChooser();
        cal_masuk = new com.toedter.calendar.JDateChooser();
        btn_laporan = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Form Karyawan");

        jLabel2.setText("ID");

        jLabel3.setText("Nama");

        jLabel4.setText("Alamat");

        txt_alamat.setColumns(20);
        txt_alamat.setRows(5);
        jScrollPane1.setViewportView(txt_alamat);

        cmb_agama.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Islam", "Kristen", "Protestan", "Budha", "Hindu", "Khonghucu" }));

        jLabel5.setText("Agama");

        jLabel7.setText("Tanggal Lahir");

        jLabel8.setText("Tanggal Masuk");

        jLabel9.setText("Jabatan");

        tbl_karyawan.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_karyawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_karyawanMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_karyawan);

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

        btn_tambah.setBackground(new java.awt.Color(0, 204, 0));
        btn_tambah.setText("Tambah");
        btn_tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_tambahMousePressed(evt);
            }
        });

        cal_lahir.setDateFormatString("dd-MM-yyyy");
        cal_lahir.setMaxSelectableDate(new java.util.Date(253370743289000L));

        cal_masuk.setDate(new java.util.Date(1640973689000L));
        cal_masuk.setDateFormatString("dd-MM-yyyy");

        btn_laporan.setBackground(new java.awt.Color(255, 153, 51));
        btn_laporan.setText("Laporan");
        btn_laporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_laporanMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_tambah)
                        .addGap(18, 18, 18)
                        .addComponent(btn_edit)
                        .addGap(18, 18, 18)
                        .addComponent(btn_hapus)
                        .addGap(18, 18, 18)
                        .addComponent(btn_laporan)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8))
                                .addGap(52, 52, 52)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmb_agama, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmb_jabatan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_nama)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                                    .addComponent(txt_id)
                                    .addComponent(cal_lahir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cal_masuk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(120, 120, 120))))
            .addGroup(layout.createSequentialGroup()
                .addGap(283, 283, 283)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel1)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmb_agama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmb_jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(cal_lahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(cal_masuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_tambah)
                    .addComponent(btn_hapus)
                    .addComponent(btn_edit)
                    .addComponent(btn_laporan))
                .addGap(44, 44, 44)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_hapusMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_hapusMousePressed
        try {
            String id = txt_id.getText();
            
            karyawanController.hapus(id);
            resetForm();
            initRowTabel();
        } catch(Exception ex) {
            System.out.println("Buton Edit Exception => " + ex);
        }
    }//GEN-LAST:event_btn_hapusMousePressed

    private void btn_editMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editMousePressed
        try {
           String id = txt_id.getText();
           String nama = txt_nama.getText();
           String alamat = txt_alamat.getText();
           String agama = cmb_agama.getSelectedItem().toString();
           String namaJabatan = cmb_jabatan.getSelectedItem().toString();
           Jabatan jabatan = jabatanController.namaToJabatan(namaJabatan);
           String idJabatan = jabatan.id;
           long tanggalLahir = utils.toEpoch(cal_lahir.getDate());
           long tanggalMasuk = utils.toEpoch(cal_masuk.getDate());
           
          
           
           boolean edit = karyawanController.edit(id, nama, alamat, agama, idJabatan, tanggalLahir, tanggalMasuk);
           
            if(edit) {
                JOptionPane.showMessageDialog(this, "Data berhasil diedit!");
                initRowTabel();
                resetForm();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal mengedit data!");
            }
        } catch(HeadlessException ex) {
            System.out.println("Buton Edit Exception => " + ex);
        }
    }//GEN-LAST:event_btn_editMousePressed

    private void btn_tambahMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_tambahMousePressed
        try {
           String id = txt_id.getText();
           String nama = txt_nama.getText();
           String alamat = txt_alamat.getText();
           String agama = cmb_agama.getSelectedItem().toString();
           String namaJabatan = cmb_jabatan.getSelectedItem().toString();
           Jabatan jabatan = jabatanController.namaToJabatan(namaJabatan);
           String idJabatan = jabatan.id;
           long tanggalLahir = utils.toEpoch(cal_lahir.getDate());
           long tanggalMasuk = utils.toEpoch(cal_masuk.getDate());
           
           boolean tambah =  karyawanController.tambah(id, nama, alamat, agama, idJabatan, tanggalLahir, tanggalMasuk);
            
            if(tambah) {
                JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!");
                initRowTabel();
                resetForm();
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menambah data!");
            }
        } catch(HeadlessException ex) {
            System.out.println("Buton Tambah Exception => " + ex);
        }
    }//GEN-LAST:event_btn_tambahMousePressed

    private void tbl_karyawanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_karyawanMousePressed
        int index = tbl_karyawan.getSelectedRow();
        TableModel mdl = tbl_karyawan.getModel();
        String id = mdl.getValueAt(index, 0).toString();
        
        try {
            Karyawan karyawan = karyawanController.detailKaryawan(id);
            Jabatan jabatan = jabatanController.detailJabatan(karyawan.idJabatan);
            txt_id.setEditable(false);
            txt_id.setText(karyawan.id);
            txt_nama.setText(karyawan.nama);
            txt_alamat.setText(karyawan.alamat);
            
            for(int i = 0; i < cmb_agama.getModel().getSize(); i++) {
                String item =  cmb_agama.getModel().getElementAt(i);
                if(item.equalsIgnoreCase(karyawan.agama)) {
                    cmb_agama.setSelectedIndex(i);
                    break;
                }
            }
            
            for(int i = 0; i < cmb_jabatan.getModel().getSize(); i++) {
                String item =  cmb_jabatan.getModel().getElementAt(i);
                if(item.equalsIgnoreCase(jabatan.namaJabatan)) {
                    cmb_jabatan.setSelectedIndex(i);
                    break;
                }
            }
            
            cal_lahir.setDate(utils.toDate(karyawan.tanggalLahir));
            cal_masuk.setDate(utils.toDate(karyawan.tanggalMasuk));
            
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }//GEN-LAST:event_tbl_karyawanMousePressed

    private void btn_laporanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_laporanMousePressed
        utils.bukaLaporan("laporan_karyawan", karyawanController.con);
    }//GEN-LAST:event_btn_laporanMousePressed

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
            java.util.logging.Logger.getLogger(FormKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormKaryawan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_laporan;
    private javax.swing.JButton btn_tambah;
    private com.toedter.calendar.JDateChooser cal_lahir;
    private com.toedter.calendar.JDateChooser cal_masuk;
    private javax.swing.JComboBox<String> cmb_agama;
    private javax.swing.JComboBox<String> cmb_jabatan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.ButtonGroup kelamin;
    private javax.swing.JTable tbl_karyawan;
    private javax.swing.JTextArea txt_alamat;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_nama;
    // End of variables declaration//GEN-END:variables
}
