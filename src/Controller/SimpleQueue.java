package Controller;

import javax.swing.*;

public class SimpleQueue {
    private String[][] queuedata; // 2D array to store queue data
    private int front; // Points to the front of the queue
    private int rear; // Points to the rear of the queue
    private int size; // Current size of the queue
    private int capacity; // Maximum capacity of the queue

    // Constructor to initialize the queue with a given capacity
    public SimpleQueue(int capacity) {
        this.capacity = capacity;
        this.queuedata = new String[capacity][6]; // 6 columns: id, name, company, price, quantity, date
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    // Method to add an item to the queue
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

    // Method to remove an item from the queue
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

    // Method to peek at the front item of the queue
    public String[] peekFront() {
        if (isEmpty()) {
            return null;
        }
        return queuedata[front];
    }

    // Check if the queue is empty
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

    // Check if the queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Check if the queue is full
    public boolean isFull() {
        return size == capacity;
    }

    // Get the current size of the queue
    public int getSize() {
        return size;
    }

    // Get the rear index of the queue
    public int getRear() {
        return rear;
    }

    // Get the front index of the queue
    public int getFront() {
        return front;
    }

    // Get the entire queue data
    public String[][] getQueueData() {
        return queuedata;
    }
}
