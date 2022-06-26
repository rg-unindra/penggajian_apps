/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

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
//    hari terakhirnya 1 Juni 2022 00:00:00
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
}
