/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penggajian;

/**
 *
 * @author Farhan Fadila
 */
public class Penggajian {
    public int id;
    public String idKaryawan;
    public String idUser;
    public double totalPotongan;
    public double gajiBersih;
    public long tanggal;
    public long tanggalDibuat;
    
    public Penggajian(
            int id,
            String idKaryawan,
            String idUser,
            double totalPotongan,
            double gajiBersih,
            long tanggal,
            long tanggalDibuat
    ) {
        this.id = id;
        this.idKaryawan = idKaryawan;
        this.idUser = idUser;
        this.totalPotongan = totalPotongan;
        this.gajiBersih = gajiBersih;
        this.tanggal = tanggal;
        this.tanggalDibuat = tanggalDibuat;
    }
}
