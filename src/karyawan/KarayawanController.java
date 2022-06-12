/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karyawan;

import Database.Koneksi;
/**
 *
 * @author Farhan Fadila
 */
public class KarayawanController extends Koneksi {
  
  
    
    public KarayawanController() {
        if(con == null) {
            start();
        }
    }
     
    
}
