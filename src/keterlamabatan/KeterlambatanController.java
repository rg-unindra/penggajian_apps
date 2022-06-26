/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keterlamabatan;

import Database.Koneksi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import karyawan.KaryawanController;
import utils.Utils;

/**
 *
 * @author Farhan Fadila
 */
public class KeterlambatanController extends Koneksi {
     private final Utils utils = new Utils();
     public KeterlambatanController() {
        if(con == null) {
            start();
        }
    }
     
     
    public List<Keterlambatan> dataKeterlambatan() {
        List<Keterlambatan> temp = new ArrayList<>();

        ResultSet result = executeQuery("SELECT * FROM `keterlambatan`");

        try {
            while(result.next()) {
               temp.add(new Keterlambatan(result.getInt(1), result.getString(2), result.getInt(3), result.getLong(4), result.getString(5)));
            }
        } catch (SQLException ex) {
           Logger.getLogger(KaryawanController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return temp;
    }
    
    public List<Keterlambatan> dataKeterlambatanSatuBulan(String idKaryawan, Date bulan) {
        List<Keterlambatan> temp = new ArrayList<>();
        long hariPertama = utils.dayStart(bulan);
        long hariTerakhir = utils.dayEnd(bulan);

        ResultSet result = executeQuery("SELECT * FROM `keterlambatan` WHERE `id_karyawan` = '" + idKaryawan + "' AND `tanggal` >= '" + hariPertama + "' AND `tanggal` <= '" + hariTerakhir + "'");

        try {
            while(result.next()) {
               temp.add(new Keterlambatan(result.getInt(1), result.getString(2), result.getInt(3), result.getLong(4), result.getString(5)));
            }
        } catch (SQLException ex) {
           Logger.getLogger(KaryawanController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return temp;
    }
    
     public Keterlambatan detailKeterlambatan(String id) {
        
        ResultSet result = executeQuery("SELECT * FROM `keterlambatan` WHERE id_keterlambatan = '" + id + "'");

        try {
            if(result.next()) {
               return new Keterlambatan(result.getInt(1), result.getString(2), result.getInt(3), result.getLong(4), result.getString(5));
            }
        } catch (SQLException ex) {
           Logger.getLogger(KaryawanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
     public boolean tambah( 
        String idKaryawan,
        int jam,
        long tanggal,
        String keterangan
    ) {
        try {
          Object[] object = {idKaryawan, jam, tanggal, keterangan};
          executeQuery2("INSERT INTO `keterlambatan` (id_karyawan, jam, tanggal, keterangan) VALUES " + objectToString(object));
          return true;
        } catch(Exception ex) {
            System.out.println("Tambah Exception => " + ex);
            return false;
        }
    }
     
    public boolean edit(
        String id,
        String idKaryawan,
        int jam,
        long tanggal,
        String keterangan
    ) {
        try {
           executeQuery2("UPDATE `keterlambatan` SET id_karyawan = '" + idKaryawan + "', jam = '" + jam + "' , tanggal = '" + tanggal + "', keterangan = '"
                  + keterangan + "' WHERE id_keterlambatan = '" + id + "'");
          return true;
        } catch(Exception ex) {
            System.out.println("Edit Exception => " + ex);
            return false;
        }
    }
     
    public boolean hapus(String id) {
        try {
            executeQuery2("DELETE FROM `keterlambatan` WHERE `id_keterlambatan` = '" + id + "'");
            return true;
        } catch(Exception ex) {
            return false;
        }
    }
}
