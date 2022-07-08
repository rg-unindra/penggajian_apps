/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lembur;

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
public class LemburController extends Koneksi {
     private final Utils utils = new Utils();
     public LemburController() {
        if(con == null) {
            start();
        }
    }
     
     
    public List<Lembur> dataLembur() {
        List<Lembur> temp = new ArrayList<>();

        ResultSet result = executeQuery("SELECT * FROM `lembur`");

        try {
            while(result.next()) {
               temp.add(new Lembur(result.getInt(1), result.getString(2), result.getInt(3), result.getLong(4), result.getString(5)));
            }
        } catch (SQLException ex) {
           Logger.getLogger(LemburController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return temp;
    }
    
    public List<Lembur> dataLemburSatuBulan(String idKaryawan, Date bulan) {
        List<Lembur> temp = new ArrayList<>();
        long hariPertama = utils.dayStart(bulan);
        long hariTerakhir = utils.dayEnd(bulan);

        ResultSet result = executeQuery("SELECT * FROM `lembur` WHERE `id_karyawan` = '" + idKaryawan + "' AND `tanggal` >= '" + hariPertama + "' AND `tanggal` <= '" + hariTerakhir + "'");

        try {
            while(result.next()) {
               temp.add(new Lembur(result.getInt(1), result.getString(2), result.getInt(3), result.getLong(4), result.getString(5)));
            }
        } catch (SQLException ex) {
           Logger.getLogger(LemburController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return temp;
    }
    
     public Lembur detailLembur(String id) {
        
        ResultSet result = executeQuery("SELECT * FROM `lembur` WHERE id_lembur = '" + id + "'");

        try {
            if(result.next()) {
               return new Lembur(result.getInt(1), result.getString(2), result.getInt(3), result.getLong(4), result.getString(5));
            }
        } catch (SQLException ex) {
           Logger.getLogger(LemburController.class.getName()).log(Level.SEVERE, null, ex);
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
          executeQuery2("INSERT INTO `lembur` (id_karyawan, jam, tanggal, keterangan) VALUES " + objectToString(object));
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
           executeQuery2("UPDATE `lembur` SET id_karyawan = '" + idKaryawan + "', jam = '" + jam + "' , tanggal = '" + tanggal + "', keterangan = '"
                  + keterangan + "' WHERE id_lembur = '" + id + "'");
          return true;
        } catch(Exception ex) {
            System.out.println("Edit Exception => " + ex);
            return false;
        }
    }
     
    public boolean hapus(String id) {
        try {
            executeQuery2("DELETE FROM `lembur` WHERE `id_lembur` = '" + id + "'");
            return true;
        } catch(Exception ex) {
            return false;
        }
    }
}
