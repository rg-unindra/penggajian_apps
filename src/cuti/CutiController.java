/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuti;

import Database.Koneksi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Farhan Fadila
 */
public class CutiController extends Koneksi {
    
    public CutiController() {
        if(con == null) {
            start();
        }
    }
    
     public List<Cuti> dataCuti() {
        List<Cuti> temp = new ArrayList<>();

        ResultSet result = executeQuery("SELECT * FROM `cuti`");

        try {
            while(result.next()) {
               temp.add(new Cuti(result.getInt(1), result.getString(2), result.getLong(3), result.getInt(4), result.getString(5)));
            }
        } catch (SQLException ex) {
           Logger.getLogger(CutiController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return temp;
    }
     
     public Cuti detailCuti(String id) {
        
        ResultSet result = executeQuery("SELECT * FROM `cuti` WHERE id_cuti = '" + id + "'");

        try {
            if(result.next()) {
               return new Cuti(result.getInt(1), result.getString(2), result.getLong(3), result.getInt(4), result.getString(5));
            }
        } catch (SQLException ex) {
           Logger.getLogger(CutiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     
     
    public boolean tambah( 
        String idKaryawan,
        long tanggal,
        int jumlahHari,
        String keterangan
    ) {
        try {
          Object[] object = {idKaryawan, tanggal, jumlahHari, keterangan};
          executeQuery2("INSERT INTO `Cuti` (id_karyawan, tanggal, jumlah_hari, keterangan) VALUES " + objectToString(object));
          return true;
        } catch(Exception ex) {
            System.out.println("Tambah Exception => " + ex);
            return false;
        }
    }
    
    public boolean edit(
        int id,
        String idKaryawan,
        long tanggal,
        int jumlahHari,
        String keterangan
    ) {
        try {
           executeQuery2("UPDATE `cuti` SET id_karyawan = '" + idKaryawan + "', tanggal = '" + tanggal + "' , jumlah_hari = '" + jumlahHari + "', keterangan = '"
                  + keterangan + "' WHERE id_cuti = '" + id + "'");
          return true;
        } catch(Exception ex) {
            System.out.println("Edit Exception => " + ex);
            return false;
        }
    }
    
    public boolean hapus(int id) {
        try {
            executeQuery2("DELETE FROM `cuti` WHERE `id_cuti` = '" + id + "'");
            return true;
        } catch(Exception ex) {
            System.out.println(ex);
            return false;
        }
    }
}
