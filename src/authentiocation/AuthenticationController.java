/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentiocation;

import Database.Koneksi;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public boolean tambah(
        String username, 
        String password,
        JFrame form
    ) {
        try {
           ResultSet existUser = executeQuery("SELECT * FROM `user` WHERE `username` = '" + username + "'");
           Object[] userObject = {username, password};
           if(existUser.next()) {
               JOptionPane.showMessageDialog(form, "Maaf, username telah digunakan!");
               return false;
           }
           
           executeQuery2("INSERT INTO `user` (username, password) VALUES " + objectToString(userObject));
           return true;
        } catch(HeadlessException | SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }
    
    
    public boolean login(String username, String password) {
     try {
        ResultSet existUser = executeQuery("SELECT * FROM `user` WHERE `username` = '" + username + "' AND `password` = '" + password + "'");
        
        System.out.println("Username " + username + " Password " + password);
         
       if(existUser.next()) {
           user = new User(existUser.getInt(1), existUser.getString(2), existUser.getString(3));
           
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
                    user = new User(existUser.getInt(1), existUser.getString(2), existUser.getString(3));
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
    
    public List<User> dataUser() {
        List<User> temp = new ArrayList<>();

        ResultSet result = executeQuery("SELECT * FROM `user`");

        try {
            while(result.next()) {
               temp.add(new User(result.getInt(1), result.getString(2), result.getString(3)));
            }
        } catch (SQLException ex) {
           Logger.getLogger(AuthenticationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return temp;
    }
    
    public boolean hapus(int id) {
        try {
            if(id == user.id) {
                return false;
            }
            executeQuery2("DELETE FROM `user` WHERE `id_user` = '" + id + "'");
            return true;
        } catch(Exception ex) {
            return false;
        }
    }
    
    public boolean edit(
        int id,
        String username,
        String password,
        JFrame form
    ) {
        try {
           ResultSet existUser = executeQuery("SELECT * FROM `user` WHERE `username` = '" + username + "'");
           if(existUser.next()) {
               JOptionPane.showMessageDialog(form, "Maaf, username telah digunakan!");
               return false;
           }
           executeQuery2("UPDATE `user` SET username = '" + username + "' , password = '" + password + "' WHERE id_user = '" + id + "'");
          return true;
        } catch(Exception ex) {
            System.out.println("Edit Exception => " + ex);
            return false;
        }
    }
}
