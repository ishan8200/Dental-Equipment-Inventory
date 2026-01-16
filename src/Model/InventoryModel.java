/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Ishan Maharjan
 *  Represents a product in the inventory.
 *  Contains details such as product ID, name, company, price, quantity, and added date.
 */
public class InventoryModel {
    private String productID; // Unique identifier for the product
    private String productName; // Name of the product
    private String productCompany; // Company manufacturing the product
    private double productPrice; // Price of the product
    private int productQuantity; // Quantity available in inventory
    private String addedDate; // Date the product was added to the inventory
    private String status; // Status of the product (optional, e.g., "Available", "Out of Stock")

    /**
     * Constructor to initialize the product details.
     * @param productID Unique identifier for the product.
     * @param productName Name of the product.
     * @param productCompany Company manufacturing the product.
     * @param productPrice Price of the product.
     * @param productQuantity Quantity available in inventory.
     * @param addedDate Date the product was added to the inventory.
     */
    public InventoryModel(String productID, String productName, String productCompany, double productPrice, int productQuantity, String addedDate) {
        this.productID = productID;
        this.productName = productName;
        this.productCompany = productCompany;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.addedDate = addedDate;
    }

    public InventoryModel(String productID, String productName, double productPrice, int productQuantity, String status) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.status = "Pending";
    }
    // Getters and setters for all fields

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCompany() {
        return productCompany;
    }

    public void setProductCompany(String productCompany) {
        this.productCompany = productCompany;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}
