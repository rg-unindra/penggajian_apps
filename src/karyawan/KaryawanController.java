/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karyawan;

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
public class KaryawanController extends Koneksi {
    List<Karyawan> karyawan;
  
    
    public KaryawanController() {
        if(con == null) {
            start();
        }
    }
     
    public List<Karyawan> dataKaryawan(boolean loadFromCache) {
        List<Karyawan> temp = new ArrayList<>();

        if(loadFromCache && karyawan != null) {
            return karyawan;
        }

        ResultSet result = executeQuery("SELECT * FROM `karyawan` ORDER BY `nama` ASC");

        try {
            while(result.next()) {
               temp.add(new Karyawan(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getLong(6), result.getLong(7)));
            }
        } catch (SQLException ex) {
           Logger.getLogger(KaryawanController.class.getName()).log(Level.SEVERE, null, ex);
        }

        karyawan = temp;

        return temp;
    }
    
    
    public boolean tambah( 
        String id,
        String nama,
        String alamat,
        String agama,
        String idJabatan,
        long tanggalLahir,
        long tanggalMasuk
    ) {
        try {
          Object[] object = {id, nama, alamat, agama, idJabatan, tanggalLahir, tanggalMasuk};
          executeQuery2("INSERT INTO `karyawan` (id_karyawan, nama, alamat, agama, id_jabatan, tanggal_lahir, tanggal_masuk) VALUES " + objectToString(object));
          return true;
        } catch(Exception ex) {
            System.out.println("Tambah Jabatan Exception => " + ex);
            return false;
        }
    }
    
    public boolean edit( 
        String id,
        String nama,
        String alamat,
        String agama,
        String idJabatan,
        long tanggalLahir,
        long tanggalMasuk
    ) {
        try {
          executeQuery2("UPDATE `karyawan` SET nama = '" + nama + "', alamat = '" + alamat + "' , agama = '" + agama + "', id_jabatan = '"
                  + idJabatan + "', tanggal_lahir = '" + tanggalLahir + "', tanggal_masuk = '" + tanggalMasuk  + "' WHERE id_karyawan = '" + id + "'");
        
          return true;
        } catch(Exception ex) {
            System.out.println("Tambah Jabatan Exception => " + ex);
            return false;
        }
    }
    
    public Karyawan detailKaryawan(String id) {
        
        ResultSet result = executeQuery("SELECT * FROM `karyawan` WHERE id_karyawan = '" + id + "'");

        try {
            if(result.next()) {
               return new Karyawan(result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getLong(6), result.getLong(7));
           
            }
        } catch (SQLException ex) {
           Logger.getLogger(KaryawanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public boolean hapus(String id) {
        try {
            executeQuery2("DELETE FROM `karyawan` WHERE `id_karyawan` = '" + id + "'");
            return true;
        } catch(Exception ex) {
            return false;
        }
    }
}
