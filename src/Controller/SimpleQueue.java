package Controller;


import javax.swing.*;

public class SimpleQueue {
    private String[][] queuedata;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public SimpleQueue(int capacity) {
        this.capacity = capacity;
        this.queuedata = new String[capacity][6]; // 6 columns: id, name, company, price, quantity, date
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    public void enqueue(String id, String name, String company, double price, int quantity, String date) {
        if (isFull()) {
            JOptionPane.showMessageDialog(null, "Queue is Full");
            System.out.println("Queue is Full");
            return;
        }

        rear = (rear + 1) % capacity;
        queuedata[rear][0] = id;
        queuedata[rear][1] = name;
        queuedata[rear][2] = company;
        queuedata[rear][3] = String.valueOf(price);
        queuedata[rear][4] = String.valueOf(quantity);
        queuedata[rear][5] = date;
        size++;
    }

    public void dequeue() {
        if (isEmpty()) {
            JOptionPane.showMessageDialog(null, "Queue is Empty");
            return ;
        }

        String[] item = new String[6];
        for (int i = 0; i < 6; i++) {
            item[i] = queuedata[front][i];
        }

        // Clear the front position
        for (int i = 0; i < 6; i++) {
            queuedata[front][i] = null;
        }

        front = (front + 1) % capacity;
        size--;

    }

    public String[] peekFront() {
        if (isEmpty()) {
            return null;
        }
        return queuedata[front];
    }

    public String[] peekRear() {
        if (isEmpty()) {
            JOptionPane.showMessageDialog(null, "Queue is Empty");
            return null;
        }

        String[] item = new String[6];
        for (int i = 0; i < 6; i++) {
            item[i] = queuedata[rear][i];
        }
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int getSize() {
        return size;
    }

    public int getRear() {
        return rear;
    }

    public int getFront() {
        return front;
    }

    public String[][] getQueueData() {
        return queuedata;
    }
}
