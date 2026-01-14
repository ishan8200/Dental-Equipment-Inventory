/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.InventoryModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Ripple Device
 */
public class Structures {
    
    static int size=10;
    static int front=-1, rear=-1, top=-1;
    static String [][] stack = new String [size][6];

    Operations op = new Operations();
    LinkedList <InventoryModel> list = op.getList();
    
    
    public static String[][] pushToStack(String  [][] stack,String id, String name, String company, double price, int quantity, String date){
        if (top==size-1)
            return stack;
        top++;
        stack[top][0]=id;
        stack[top][1]=name;
        stack[top][2]=company;
        stack[top][3]=String.valueOf(price);
        stack[top][4]=String.valueOf(quantity);
        stack[top][5]=date;
        return stack;

    }
    
    public static void loadFromStack(JTable table, String [][] stack){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        if (model.getColumnCount()==3){
            for(int i =top ; i>=0; i--){
                if (stack [i][0]!=null){
                    model.addRow(new Object []{stack[i][0],stack[i][1],stack[i][2]});
                }
            }
        }
        else{
            for(int i =top ; i>=0; i--){
                if (stack [i][0]!=null){
                    model.addRow(new Object []{stack[i][0],stack[i][1],stack[i][2],stack[i][3],stack[i][4],stack[i][5]});
                }
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

    public static void loadValuetoTable(JTable table, LinkedList<InventoryModel> list, int value){

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        Object[] row ={
                list.get(value).getProductID(),
                list.get(value).getProductName(),
                list.get(value).getProductCompany(),
                list.get(value).getProductPrice(),
                list.get(value).getProductQuantity(),
                list.get(value).getAddedDate()
        };
        model.addRow(row);
    }

    public void loadlistToTable(JTable table, LinkedList<InventoryModel> list, List<Integer> value){

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (Integer integer : value) {
            Object[] row = {
                    list.get(integer).getProductID(),
                    list.get(integer).getProductName(),
                    list.get(integer).getProductCompany(),
                    list.get(integer).getProductPrice(),
                    list.get(integer).getProductQuantity(),
                    list.get(integer).getAddedDate()
            };
            model.addRow(row);
        }

    }

}
