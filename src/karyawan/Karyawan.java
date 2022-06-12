/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karyawan;

/**
 *
 * @author Farhan Fadila
 */
public class Karyawan {
    String id;
    String nama;
    String alamat;
    String agama;
    String idJabatan;
    long tanggalLahir;
    long tanggalMasuk;
    
    
    public Karyawan(
        String id,
        String nama,
        String alamat,
        String agama,
        String idJabatan,
        long tanggalLahir,
        long tanggalMasuk
    ) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.agama = agama;
        this.idJabatan = idJabatan;
        this.tanggalLahir = tanggalLahir;
        this.tanggalMasuk = tanggalMasuk;
    }
    
    
    @Override
    public String toString() {
        return "Karyawan(" + id + "," + nama + "," + alamat + "," + agama
                + ","+ idJabatan + "," + tanggalLahir + "," + tanggalMasuk + ")";
    }
}
