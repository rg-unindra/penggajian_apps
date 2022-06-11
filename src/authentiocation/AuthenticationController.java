/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentiocation;

import Database.Koneksi;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Farhan Fadila
 */
public class AuthenticationController extends Koneksi {
    User user;
    
    public AuthenticationController() {
        if(con == null) {
            start();
        }
    }
    
    
    boolean login(String username, String password) {
     try {
        ResultSet existUser = executeQuery("SELECT * FROM user WHERE username = " + username + " AND passoword = " + password);
         
       if(existUser.next()) {
           user = new User(existUser.getInt(1), existUser.getString(2));
           return true;
       } else {
           throw new Exception("User not found!");
       }
     } catch(Exception ex) {
        return false;
     }
    }
    
    
    boolean isLoggedIn () {
       return user != null;
    }
    
    void checkCurrentSession() {
        
    }
    
    void save() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        Object[] sessionObject = {user.id,timeStamp};
        executeQuery2("INSERT INTO session (id_user, login_time) VALUES " + objectToString(sessionObject));
    }
    
    void logout() {
        user = null;
        executeQuery2("DELETE FROM session WHERE id_user = " + user.id_user);
    }
}
