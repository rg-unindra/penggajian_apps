/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Farhan Fadila
 */
public class Utils {
     final Locale locale = new Locale("id", "ID");
     final NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
    
     
     
     public String formatToRupiah(Number value) {
         nf.setMinimumFractionDigits(0);
         return nf.format(value);
     }
     
    
    public Date toDate(long epochTime) {
        return new Date(epochTime);
    }
    
    public long epochTimeNow() {
        return Instant.now().toEpochMilli();
    }
    
    public long toEpoch(Date date) {
        return date.toInstant().toEpochMilli();
    }
    
    public String dMY(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        return format.format(date);
    }

//    Ini buat dapet hari pertama bulan dari date di property
//    Misalkan hari pertama dari bulan juni 2022
//    hari pertamanya 1 Juni 2022 00:00:00
//    Dikemabliin dalam bentuk epoch `1656608399000`
    public long dayStart(Date date) {
        LocalDate ld = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = ld.getMonthValue();
        int year = ld.getYear(); 
        YearMonth ym =  YearMonth.of(year, month);
        return toEpoch(Date.from(ym.atDay(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }
    
//    Ini buat dapet hari terakhir bulan dari date di property
//    Misalkan hari terakhir dari bulan juni 2022
//    hari terakhirnya 30 Juni 2022 23:59:59
//    Dikemabliin dalam bentuk epoch `1656608399000`
     public long dayEnd(Date date) {
        LocalDate ld = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = ld.getMonthValue();
        int year = ld.getYear();
        YearMonth ym =  YearMonth.of(year, month);
        return toEpoch(Date.from(ym.atEndOfMonth().atStartOfDay(ZoneId.systemDefault()).toInstant().plusSeconds(86399)));
    }
     
    public void bukaLaporan(String namaFile, Connection con) {
        try {
            String reportDirectory = System.getProperty("user.dir") + "/src/laporan/" + namaFile;
            String reportSource = reportDirectory  + ".jrxml";
            String reportDestination = reportDirectory + ".jasper";

            JasperReport report = JasperCompileManager.compileReport(reportSource);
            JasperPrint print = JasperFillManager.fillReport(report, null, con);
            JasperExportManager.exportReportToHtmlFile(print, reportDestination);
            JasperViewer viewer = new JasperViewer(print, false, locale);
            viewer.setAlwaysOnTop(true);
            viewer.setVisible(true);
        } catch(JRException ex) {
            System.out.println(ex);
        }
    }
}
