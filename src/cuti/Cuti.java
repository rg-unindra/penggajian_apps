/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuti;

/**
 *
 * @author Farhan Fadila
 */
public class Cuti {
    public int id;
    public String idKaryawan;
    public long tanggal;
    public int jumlahHari;
    public String keterangan;
    
    public Cuti(
        int id,
        String idKaryawan,
        long tanggal,
        int jumlahHari,
        String keterangan
    ) {
        this.id = id;
        this.idKaryawan = idKaryawan;
        this.tanggal = tanggal;
        this.jumlahHari = jumlahHari;
        this.keterangan = keterangan;
    }
    
    
    @Override
    public String toString() {
        return "Cuti(" + id + "," + idKaryawan + "," + tanggal + "," + jumlahHari
                + ","+ keterangan + ")";
    }
}
