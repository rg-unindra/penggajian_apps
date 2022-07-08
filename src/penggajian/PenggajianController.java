/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penggajian;

import Database.Koneksi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Utils;

/**
 *
 * @author Farhan Fadila
 */
public class PenggajianController extends Koneksi {
    private final Utils utils = new Utils();
    public PenggajianController() {
        if(con == null) {
            start();
        }
    }
    
    
    public boolean simpan(List<Object[]> data, List<Object[]> dataLaporan, Date bulan) {
        try {
            final long tanggalAwal = utils.dayStart(bulan);
            final long tanggalAkhir = utils.dayEnd(bulan);
            executeQuery2("DELETE FROM `penggajian` WHERE tanggal >= '" + tanggalAwal + "' AND tanggal <= '" + tanggalAkhir + "'");
           
            String query = "INSERT INTO `penggajian` (id_karyawan, id_user, total_lembur, total_potongan, gaji_bersih, tanggal, tanggal_dibuat) VALUES ";
            
            for(int i = 0; i < data.size(); i++) {
                boolean isLast = i == data.size() - 1;
                query += objectToString(data.get(i));
                if(!isLast) {
                    query += ",";
                }
            }
            
            executeQuery2(query);
            simpanLaporan(dataLaporan);
            
            return true;
        } catch(Exception ex) {
            Logger.getLogger(PenggajianController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List<Penggajian> dataPenggajian(long tanggalAwal, long tanggalAkhir) {
        try {
            List<Penggajian> temp = new ArrayList<>();

            ResultSet result = executeQuery("SELECT * FROM `penggajian` WHERE tanggal >= '" + tanggalAwal + "' AND tanggal <= '" + tanggalAkhir + "'");
            while(result.next()) {
               temp.add(new Penggajian(result.getInt(1), result.getString(2), result.getString(3), result.getDouble(4), result.getDouble(5), result.getDouble(6), result.getLong(6), result.getLong(7)));
            }
            return temp;
        }  catch(SQLException ex) {
            Logger.getLogger(PenggajianController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    private void simpanLaporan(List<Object[]> dataLaporan) {
        try {
            executeQuery2("DELETE FROM `laporan_penggajian`");
            String query = "INSERT INTO `laporan_penggajian` (id_karyawan, nama_karyawan, nama_jabatan, gaji_pokok, tunjangan, total_lembur, total_potongan, gaji_bersih, tanggal) VALUES ";
            
            for(int i = 0; i < dataLaporan.size(); i++) {
                boolean isLast = i == dataLaporan.size() - 1;
                query += objectToString(dataLaporan.get(i));
                if(!isLast) {
                    query += ",";
                }
            }
            
            executeQuery2(query);
        } catch(Exception ex) {
            Logger.getLogger(PenggajianController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
