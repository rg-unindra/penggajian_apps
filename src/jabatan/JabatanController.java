/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jabatan;

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
public class JabatanController  extends Koneksi{
     
    public JabatanController() {
      if(con == null) {
            start();
       }
    }
    
    
    public boolean tambah(String id, String nama, long gapok, long tunjangan) {
        try {
          Object[] object = {id, nama, gapok, tunjangan};
          executeQuery2("INSERT INTO jabatan (id_jabatan, nama, gaji_pokok, tunjangan) " + objectToString(object));
          return true;
        } catch(Exception ex) {
            System.out.println("Tambah Jabatan Exception => " + ex);
            return false;
        }
    }
    
    
     public boolean edit(String id, String nama, long gapok, long tunjangan) {
        try {
          executeQuery2("UPDATE jabatan SET nama = '" + nama + "', gaji_pokok = '" + gapok + "' , tunjangan = '" + tunjangan + "' WHERE id_jabatan = '" + id + "'");
          return true;
        } catch(Exception ex) {
            System.out.println("Tambah Jabatan Exception => " + ex);
            return false;
        }
    }
    
    
    public List<Jabatan> dataJabatan() {
            List<Jabatan> temp = new ArrayList<>();
        
            ResultSet result = executeQuery("SELECT * FROM `jabatan`");

            try {
                while(result.next()) {
                   temp.add(new Jabatan(result.getString(1), result.getString(2), result.getLong(3), result.getLong(4)));
                }
            } catch (SQLException ex) {
               Logger.getLogger(JabatanController.class.getName()).log(Level.SEVERE, null, ex);
            }

            return temp;
    }
    
    
    public boolean hapus(String id) {
        try {
            executeQuery2("DELETE FROM `jabatan` WHERE `id_jabatan` = '" + id + "'");
            return true;
        } catch(Exception ex) {
            return false;
        }
    }
}