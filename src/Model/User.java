/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.LinkedList;

/**
 * @author Ishan Maharjan
 * Represents a regular user in the system.
 */
public class User {
    private String username; // User username
    private String password; // User password
    private String name; // User full name


    LinkedList <InventoryModel> userCart = new LinkedList<>();
    /**
     * Constructor to initialize the user details.
     * @param username User username.
     * @param password User password.
     * @param name User full name.
     */
    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    // Getters and setters for all fields

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
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public LinkedList<InventoryModel> getUserCart() {
        return userCart;
    }

    public void setUserCart(LinkedList<InventoryModel> userCart) {
        this.userCart = userCart;
    }
}
