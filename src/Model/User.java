/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.LinkedList;

/**
 *
 * @author Ripple Device
 */
public class User {
    String username;
    String password;

    String Name;

    LinkedList <InventoryModel> userCart = new LinkedList<>();
    public User(String username, String password, String Name) {
        this.username = username;
        this.password = password;
        this.Name = Name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public LinkedList<InventoryModel> getUserCart() {
        return userCart;
    }

    public void setUserCart(LinkedList<InventoryModel> userCart) {
        this.userCart = userCart;
    }
}
