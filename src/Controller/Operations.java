/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.InventoryModel;

import javax.swing.*;
import java.util.LinkedList;




/**
 *
 * @author Ishan Maharjan
 */
public class Operations {
    
    LinkedList<InventoryModel> list = new LinkedList<>();

    public void setList(LinkedList<InventoryModel> listSend){
        this.list=listSend;
    }
    public LinkedList<InventoryModel> getList(){
        return this.list;
    }
    //Setting up the initial components
    public void initialData() {
        list.add(new InventoryModel("001", "X-Ray Machine", "Xpedent", 85000.00, 5, "2025-01-15"));
        list.add(new InventoryModel("002", "Dental Chair", "Gladent", 450000.00, 10, "2025-02-20"));
        list.add(new InventoryModel("003", "Sterilizer", "Wintech", 3000.00, 7, "2025-03-10"));
        list.add(new InventoryModel("004", "Scaler", "Woodpecker", 5000.00, 15, "2025-04-05"));
        list.add(new InventoryModel("005", "Curing Light", "BrightSmile", 5000.00, 20, "2025-05-12"));
    }
    public boolean addProduct(JPanel panel, String id, String name, String company, String price, String quantity, String date)
    throws IllegalArgumentException, NumberFormatException{
        // Logic to add product to the inventory
        boolean goAhead = validateData(panel, id, name, company, price, quantity, date);
        if (!goAhead) {
            return false;
        }
        for (InventoryModel l : this.list) {
                if (l.getProductID().equals(id)) {
                    JOptionPane.showMessageDialog(panel, "Product ID already exists", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }

            }
        InventoryModel newProduct = new InventoryModel(id, name, company, Double.parseDouble(price), Integer.parseInt(quantity), date);
        this.list.add(newProduct);
        return true;
    }

    
    public boolean updateProduct(JPanel panel, String productId, String newName, String newCompany, String newPrice, String newQuantity, String newDate) {
        // Logic to update product in the inventory
        
        boolean goAhead = validateData(panel, productId, newName, newCompany, newPrice, newQuantity, newDate);
        if (!goAhead) {
            return false;
        }
        try{
            InventoryModel inventoryModel = findProductID(productId);
            inventoryModel.setProductID(productId);
            inventoryModel.setProductName(newName);
            inventoryModel.setProductCompany(newCompany);
            inventoryModel.setProductPrice(Double.parseDouble(newPrice));
            inventoryModel.setProductQuantity(Integer.parseInt(newQuantity));
            inventoryModel.setAddedDate(newDate);
            return true;
        }catch (NullPointerException e){
            JOptionPane.showMessageDialog(panel, "Please fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(panel, "Please enter valid numeric values for Price and Quantity", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void deleteProduct(InventoryModel model, JPanel panel) {
        // Logic to delete product from the inventory
        try {
                this.list.remove(model);
                JOptionPane.showMessageDialog(panel, "Product deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                return;
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(panel, "Product ID not found", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
       

    }

    public boolean validateData(JPanel panel,String id, String name, String company, String price, String quantity, String date) {
        // Logic to validate data before adding or updating product
        try{
            if(id.isEmpty()|| name.isEmpty()||
                    company.isEmpty()|| price.isEmpty()||
                    quantity.isEmpty()|| date.isEmpty()){
                JOptionPane.showMessageDialog(panel, "Please fill all the fields", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            if (Double.parseDouble(price) <= 0) {
                JOptionPane.showMessageDialog(panel, "Please enter a valid price", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (Integer.parseInt(quantity) <= 0) {
                JOptionPane.showMessageDialog(panel, "Please enter a valid quantity", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            try {
                int year = Integer.parseInt(date.substring(0, 4));
                int month = Integer.parseInt(date.substring(5, 7));
                int day = Integer.parseInt(date.substring(8, 10));


                if (year < 2022 || year > 2025) {
                    JOptionPane.showMessageDialog(panel, "Invalid year in date","Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }

                if (month < 1 || month > 12) {
                    JOptionPane.showMessageDialog(panel, "Invalid month in date","Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                if (day < 1 || day > 32) {
                    JOptionPane.showMessageDialog(panel, "Invalid day in date","Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(panel, "Invalid date format","Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

        }
        catch (NumberFormatException e){

            JOptionPane.showMessageDialog(panel, "Please enter valid numeric values for Price and Quantity", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        catch (Exception e){

            JOptionPane.showMessageDialog(panel, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public InventoryModel findProductID(String productId) throws NullPointerException {
        // Logic to find product by ID
        for (InventoryModel l : this.list) {
            if (l.getProductID().equals(productId)) {
                return l;
            }

        }
        throw new NullPointerException("Product ID not found");
    }
}
