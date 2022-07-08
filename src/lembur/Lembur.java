/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lembur;

/**
 *
 * @author Farhan Fadila
 */
public class Lembur {
    public int id;
    public String idKaryawan;
    public int jam;
    public long tanggal;
    public String keterangan;
    
    public Lembur(
         int id,
         String idKaryawan,
         int jam,
         long tanggal,
         String keterangan
    ) {
        this.id = id;
        this.idKaryawan = idKaryawan;
        this.jam = jam;
        this.tanggal = tanggal;
        this.keterangan = keterangan;
    }
    
    @Override
    public String toString() {
        return "Lembur(" + id + "," + idKaryawan + "," + jam + "," + tanggal + "," + keterangan  + ")";
    }
}
