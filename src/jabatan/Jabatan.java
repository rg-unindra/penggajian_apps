/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jabatan;

/**
 *
 * @author Farhan Fadila
 */
public class Jabatan {
    public  String id;
    public String namaJabatan;
    public long gajiPokok;
    public long tunjangan;
    
    public Jabatan(String id, String namaJabatan, long gajiPokok, long tunjangan) {
        this.id = id;
        this.namaJabatan = namaJabatan;
        this.gajiPokok = gajiPokok;
        this.tunjangan = tunjangan;
    }
    
    @Override
    public String toString() {
        return "Jabatan(" + id + "," + namaJabatan + "," + gajiPokok +  "," + tunjangan + ")";
    }
}
