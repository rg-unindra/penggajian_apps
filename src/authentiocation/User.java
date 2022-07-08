/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentiocation;

/**
 *
 * @author Farhan Fadila
 */
public class User {
    public int id;
    public String username;
    public String password;
    
    public User(
        int id, 
        String username, 
        String password
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    
    @Override
    public String toString() {
        return "User(" + id + "," + username + ")";
    }
}
