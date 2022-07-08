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
    public double gajiPokok;
    public double tunjangan;
    public double gajiPerjam;
    
    public Jabatan(
        String id, 
        String namaJabatan, 
        double gajiPokok, 
        double tunjangan,
        double gajiPerjam
        ) {
        this.id = id;
        this.namaJabatan = namaJabatan;
        this.gajiPokok = gajiPokok;
        this.tunjangan = tunjangan;
        this.gajiPerjam = gajiPerjam;
    }
    
    public double gajiPerJam() {
        return gajiPokok / 30 / 9;
    }
    
    @Override
    public String toString() {
        return "Jabatan(" + id + "," + namaJabatan + "," + gajiPokok +  "," + tunjangan + ")";
    }
}
