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
