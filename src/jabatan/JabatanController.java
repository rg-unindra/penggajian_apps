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
    List<Jabatan> jabatan;
    public JabatanController() {
      if(con == null) {
            start();
       }
    }
    
    
    public boolean tambah(
            String id, 
            String nama, 
            double gapok, 
            double tunjangan,
            double gajiPerjam
            ) {
        try {
          Object[] object = {id, nama, gapok, tunjangan, gajiPerjam};
          executeQuery2("INSERT INTO jabatan (id_jabatan, nama, gaji_pokok, tunjangan, gaji_perjam) VALUES " + objectToString(object));
          return true;
        } catch(Exception ex) {
            System.out.println("Tambah Jabatan Exception => " + ex);
            return false;
        }
    }
    
    
     public boolean edit(
             String id, 
             String nama, 
             double gapok, 
             double tunjangan, 
             double gajiPerjam
     ) {
        try {
          executeQuery2("UPDATE jabatan SET nama = '" + nama + "', gaji_pokok = '" + gapok + "' , tunjangan = '" + tunjangan + "' , gaji_perjam = '" + gajiPerjam + "' WHERE id_jabatan = '" + id + "'");
          return true;
        } catch(Exception ex) {
            System.out.println("Tambah Jabatan Exception => " + ex);
            return false;
        }
    }
    
    
    public List<Jabatan> dataJabatan(boolean loadFromCache) {
            List<Jabatan> temp = new ArrayList<>();
            
            if(loadFromCache && jabatan != null) {
                return jabatan;
            }
            
            ResultSet result = executeQuery("SELECT * FROM `jabatan`");

            try {
                while(result.next()) {
                   temp.add(new Jabatan(result.getString(1), result.getString(2), result.getDouble(3), result.getDouble(4), result.getDouble(5)));
                }
            } catch (SQLException ex) {
               Logger.getLogger(JabatanController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            jabatan = temp;

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
    
    public Jabatan namaToJabatan(String nama) {
        Jabatan temp = null;
            
        for(Jabatan item : dataJabatan(true)) {
            if(item.namaJabatan.equalsIgnoreCase(nama)) {
                temp = item; 
                break;
            }
        }
        
        return temp;
    }
    
     public Jabatan detailJabatan(String id) {
        Jabatan temp = null;
            
        for(Jabatan item : dataJabatan(true)) {
            if(item.id.equalsIgnoreCase(id)) {
                temp = item; 
                break;
            }
           
        }
        
        return temp;
    }
}
