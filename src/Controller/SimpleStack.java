package Controller;


import javax.swing.*;

public class SimpleStack {
    private String[][] stackdata;
    private int top;
    private int size;

    public SimpleStack(int capacity) {
        this.size = capacity;
        this.stackdata = new String[size][6]; // 6 columns: id, name, company, price, quantity, date
        this.top = -1;
    }

    public void push(String id, String name, String company, double price, int quantity, String date) {
        if (isFull()) {
            JOptionPane.showMessageDialog(null, "Stack is Full");
            System.out.println("Stack is Full");
            return;
        }

        top++;
        stackdata[top][0] = id;
        stackdata[top][1] = name;
        stackdata[top][2] = company;
        stackdata[top][3] = String.valueOf(price);
        stackdata[top][4] = String.valueOf(quantity);
        stackdata[top][5] = date;
    }

    public String[] pop() {
        if (isEmpty()) {
            JOptionPane.showMessageDialog(null, "Stack is Empty");
            System.out.println("Stack is Empty");
            return null;
        }

        String[] item = new String[6];
        for (int i = 0; i < 6; i++) {
            item[i] = stackdata[top][i];
        }

        // Clear the top position
        for (int i = 0; i < 6; i++) {
            stackdata[top][i] = null;
        }

        top--;
        return item;
    }

    public String[] peek() {
        if (isEmpty()) {
            JOptionPane.showMessageDialog(null, "Stack is Empty");
            return null;
        }

        String[] item = new String[6];
        for (int i = 0; i < 6; i++) {
            item[i] = stackdata[top][i];
        }
        return item;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == size - 1;
    }

    public int getTop() {
        return top;
    }

    public String[][] getStackData() {
        return stackdata;
    }

}
