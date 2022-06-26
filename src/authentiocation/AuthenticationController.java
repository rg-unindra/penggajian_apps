/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentiocation;

import Database.Koneksi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

/**
 *
 * @author Farhan Fadila
 */
public final class AuthenticationController extends Koneksi {
    public User user;
    
    public AuthenticationController() {
        if(con == null) {
            start();
        }
        checkCurrentSession();
    }
    
    
    public boolean login(String username, String password) {
     try {
        ResultSet existUser = executeQuery("SELECT * FROM `user` WHERE `username` = '" + username + "' AND `password` = '" + password + "'");
        
        System.out.println("Username " + username + " Password " + password);
         
       if(existUser.next()) {
           user = new User(existUser.getInt(1), existUser.getString(2));
           
           save();
           System.out.println("Login Success " + user);
           return true;
       } else {
           throw new Exception("User not found!");
       }
     } catch(Exception ex) {
         System.out.println("Login Exception " + ex);
        return false;
     }
    }
    
    
    public boolean isLoggedIn () {
       return user != null;
    }
    
    public void checkCurrentSession() {
        try {
            ResultSet currentSession = executeQuery("SELECT * FROM `session`");
            
            if(currentSession.next()) {
                int id =  currentSession.getInt(2);
                ResultSet existUser = executeQuery("SELECT * FROM `user` WHERE `id_user` = '" + id + "'");
                if(existUser.next()) {
                    user = new User(existUser.getInt(1), existUser.getString(2));
                    System.out.println("Succefully Loggin from Cache => " + user);
                }
            }
        } catch(SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public void save() {
        long currentTimestamp = Instant.now().toEpochMilli();
        Object[] sessionObject = {user.id,currentTimestamp};
        executeQuery2("INSERT INTO session (id_user, login_time) VALUES " + objectToString(sessionObject));
    }
    
    public boolean logout() {
        try {
            user = null;
            executeQuery2("DELETE FROM session");
            return true;
        } catch(Exception ex) {
            return false;
        }
    }
}
