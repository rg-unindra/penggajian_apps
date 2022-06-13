/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 *
 * @author Farhan Fadila
 */
public class Utils {
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
}
