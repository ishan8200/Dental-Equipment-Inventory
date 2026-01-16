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


    Operations op = new Operations();
    LinkedList <InventoryModel> list = op.getList();
    

    public static void loadFromStack(JTable table, String [][] stack,int top){
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

    public static void loadFromQueue(JTable table, String [][] queue, int front, int rear){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        if (front==-1||front>rear){
            //JOptionPane.showMessageDialog(null, "Queue is Empty");
            return;
        }
        for(int i =front ; i<=rear; i++){
            if (queue [i][0]!=null){
                model.addRow(new Object []{queue[i][0],queue[i][1],queue[i][2],queue[i][3],queue[i][4],queue[i][5]});
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

    public static void loadCartToTable(JTable table, LinkedList<InventoryModel> cartList){

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (InventoryModel l : cartList)
        {
            Object[] row ={
                    l.getProductID(),
                    l.getProductName(),
                    l.getProductCompany(),
                    l.getProductPrice(),
                    l.getProductQuantity(),
                    l.getStatus()
            };
            model.addRow(row);
        }

    }
}
