/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penggajian;

import authentiocation.AuthenticationController;
import authentiocation.User;
import jabatan.Jabatan;
import jabatan.JabatanController;
import java.awt.HeadlessException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import karyawan.Karyawan;
import karyawan.KaryawanController;
import keterlamabatan.Keterlambatan;
import keterlamabatan.KeterlambatanController;
import lembur.Lembur;
import lembur.LemburController;
import utils.Utils;

/**
 *
 * @author Farhan Fadila
 */
public class FormPenggajian extends javax.swing.JFrame {
    private final JabatanController jabatanController = new JabatanController();
    private final KaryawanController karyawanController = new KaryawanController();
    private final PenggajianController penggajianController = new PenggajianController();
    private final KeterlambatanController keterlambatanController = new KeterlambatanController();
    private final AuthenticationController authenticationController = new AuthenticationController();
    private final LemburController lemburController = new LemburController();
    private final Utils utils = new Utils();
    private DefaultTableModel model;
    /**
     * Creates new form FormPenggajian
     */
    public FormPenggajian() {
        initComponents();
        initColumnTabel();
        initRowTabel();
    }
    
    private void initColumnTabel() {
        model = new DefaultTableModel ();
        
        tbl_gaji.setModel(model);
        model.addColumn("No");
        model.addColumn("Nama");
        model.addColumn("Jabatan");
        model.addColumn("Lembur");
        model.addColumn("Potongan");
        model.addColumn("Gaji Bersih");
    }
    
    private void initRowTabel() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        final Date date = cal_bulan.getDate();
        final long tanggalAwal = utils.dayStart(date);
        final long tanggalAkhir = utils.dayEnd(date);
        try {
           final List<Penggajian> daftarPenggajian = penggajianController.dataPenggajian(tanggalAwal, tanggalAkhir);
           
           for(int i = 0; i < daftarPenggajian.size(); i++) {
               int no = i + 1;
               Penggajian penggajian = daftarPenggajian.get(i);
               Karyawan karyawan = karyawanController.detailKaryawan(penggajian.idKaryawan);
               Jabatan jabatan = jabatanController.detailJabatan(karyawan.idJabatan);
               String totalLembur = utils.formatToRupiah(penggajian.totalLembur);
               String totalPotongan = utils.formatToRupiah(penggajian.totalPotongan);
               String gajiBersih = utils.formatToRupiah(penggajian.gajiBersih);
               
               Object[] obj = new Object[6];
               
               obj[0] = no;
               obj[1] = karyawan.nama;
               obj[2] = jabatan.namaJabatan;
               obj[3] = totalLembur;
               obj[4] = totalPotongan;
               obj[5] = gajiBersih;
               
               model.addRow(obj);
           }
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void proses() {
        try {
            final Date date = cal_bulan.getDate();
            final List<Karyawan> daftarKaryawan = karyawanController.dataKaryawan(true);
            final User user = authenticationController.user;


            List<Object[]> temp = new ArrayList<>();
            List<Object[]> tempLaporan = new ArrayList<>();


            for(int i = 0; i < daftarKaryawan.size(); i++) {
                    int totalJamKeterlambatan = 0;
                    int totalJamLembur = 0;
                    Karyawan karyawan = daftarKaryawan.get(i);
                    Jabatan jabatan = jabatanController.detailJabatan(karyawan.idJabatan);
                    List<Keterlambatan> dataKeterlambatan = keterlambatanController.dataKeterlambatanSatuBulan(karyawan.id, date);
                    List<Lembur> dataLembur = lemburController.dataLemburSatuBulan(karyawan.id, date);

                    if(!dataKeterlambatan.isEmpty()) {
                        totalJamKeterlambatan = dataKeterlambatan.stream().map((item) -> item.jam).reduce(totalJamKeterlambatan, Integer::sum);
                    }
                    
                    if(!dataLembur.isEmpty()) {
                        totalJamLembur = dataLembur.stream().map((item) -> item.jam).reduce(totalJamLembur, Integer::sum);
                    }

                    double totalPotongan = utils.formatDecimal(jabatan.gajiPerJam() * totalJamKeterlambatan);
                    double totalLembur = utils.formatDecimal(jabatan.gajiPerJam() * totalJamLembur);
                    double gajiBersih = jabatan.gajiPokok + jabatan.tunjangan + totalLembur - totalPotongan;
                      
                    Object[] obj = new Object[7];
                    obj[0] = karyawan.id;
                    obj[1] = user.id;
                    obj[2] = totalLembur;
                    obj[3] = totalPotongan;
                    obj[4] = gajiBersih;
                    obj[5] = utils.dayStart(date);
                    obj[6] = utils.epochTimeNow();
                    temp.add(obj);
                    
                    Object[] objLaporan = new Object[9];
                    objLaporan[0] = karyawan.id;
                    objLaporan[1] = karyawan.nama;
                    objLaporan[2] = jabatan.namaJabatan;
                    objLaporan[3] = jabatan.gajiPokok;
                    objLaporan[4] = jabatan.tunjangan;
                    objLaporan[5] = totalLembur;
                    objLaporan[6] = totalPotongan;
                    objLaporan[7] = gajiBersih;
                    objLaporan[8] = utils.epochTimeNow();
                    tempLaporan.add(objLaporan);
            }

           final boolean sukses = penggajianController.simpan(temp, tempLaporan, date);

           if(sukses) {
              JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
              initRowTabel();
           } else {
              JOptionPane.showMessageDialog(this, "Data gagal disimpan!");
           }
        } catch(HeadlessException ex) {
            System.out.println(ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cal_bulan = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_proses = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_gaji = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txt_id_karyawan = new javax.swing.JTextField();
        btn_cari = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btn_laporan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        cal_bulan.setDate(java.util.Date.from(Instant.now())
        );
        cal_bulan.setDateFormatString("MMM, yyyy");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Form Penggajian");

        jLabel2.setText("Bulan dan Tahun");

        btn_proses.setText("Proses");
        btn_proses.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_prosesMousePressed(evt);
            }
        });
        btn_proses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_prosesActionPerformed(evt);
            }
        });

        tbl_gaji.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbl_gaji);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Data Penggajian");

        btn_cari.setText("Cari");

        jLabel4.setText("ID Karyawan");

        btn_laporan.setText("Laporan");
        btn_laporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_laporanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(278, 278, 278)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txt_id_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_cari))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(32, 32, 32)
                                .addComponent(cal_bulan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_proses)
                                .addGap(18, 18, 18)
                                .addComponent(btn_laporan)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(104, 104, 104))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(cal_bulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_proses)
                    .addComponent(btn_laporan))
                .addGap(96, 96, 96)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_id_karyawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cari)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_prosesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_prosesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_prosesActionPerformed

    private void btn_laporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_laporanActionPerformed
        utils.bukaLaporan("laporan_penggajian", penggajianController.con);
    }//GEN-LAST:event_btn_laporanActionPerformed

    private void btn_prosesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_prosesMousePressed
        proses();
    }//GEN-LAST:event_btn_prosesMousePressed

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
            java.util.logging.Logger.getLogger(FormPenggajian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPenggajian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPenggajian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPenggajian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPenggajian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_laporan;
    private javax.swing.JButton btn_proses;
    private com.toedter.calendar.JDateChooser cal_bulan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_gaji;
    private javax.swing.JTextField txt_id_karyawan;
    // End of variables declaration//GEN-END:variables
}
