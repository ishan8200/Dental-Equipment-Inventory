/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.InventoryModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;

/**
 *
 * @author Ripple Device
 */
public class Structures {
    
    static int size=10;
    static int front=-1, rear=-1, top=-1;
    static String [][] stack = new String [size][6];
    
    
    public static String[][] pushToStack(String stack[][],String id, String name, String company, double price, int quantity, String date){
        
        top++;
        stack[top][0]=id;
        stack[top][1]=name;
        stack[top][2]=company;
        stack[top][3]=String.valueOf(price);
        stack[top][4]=String.valueOf(quantity);
        stack[top][5]=date;
        return stack;

    }
    
    public static void loadFromStack(JTable table, String stack[][]){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for(int i =top ; i>=0; i--){
            if (stack [i][0]!=null){
                model.addRow(new Object []{stack[i][0],stack[i][1],stack[i][2],stack[i][3],stack[i][4],stack[i][5]});
            }
        }
        
    }
    
    public static void loadInventoryListToTable(JTable table, java.util.Collection<InventoryModel> list){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (InventoryModel l : list)
        {
            Object[] row ={
                    l.getProductID(),
                    l.getProductName(),
                    l.getProductCompany(),
                    l.getProductPrice(),
                    l.getProductQuantity(),
                    l.getAddedDate()
            };
            model.addRow(row);
        }

    }
    
}
