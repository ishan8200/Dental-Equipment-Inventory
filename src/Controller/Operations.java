/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.InventoryModel;

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
    public static void initialData(Operations operations) {
        operations.addProduct(new InventoryModel("001", "X-Ray Machine", "Xpedent", 85000.00, 5, "2025-01-15"));
        operations.addProduct(new InventoryModel("002", "Dental Chair", "Gladent", 450000.00, 10, "2025-02-20"));
        operations.addProduct(new InventoryModel("003", "Sterilizer", "Wintech", 3000.00, 7, "2025-03-10"));
        operations.addProduct(new InventoryModel("004", "Scaler", "Woodpecker", 5000.00, 15, "2025-04-05"));
        operations.addProduct(new InventoryModel("005", "Curing Light", "BrightSmile", 5000.00, 20, "2025-05-12"));
    }
    public void addProduct(InventoryModel inventoryModel) {
        // Logic to add product to the inventory
        list.add(inventoryModel);

    }

    public void readProduct(LinkedList<InventoryModel>  list) {
        // Logic to read product details from the inventory

    }
    public void updateProduct(InventoryModel inventoryModel) {
        // Logic to update product in the inventory

    }

    public void deleteProduct(int productId) {
        // Logic to delete product from the inventory

    }


}
