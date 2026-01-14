package Controller;

import Model.InventoryModel;

import java.util.LinkedList;

public class Sorting {

    public void insertionSort(LinkedList<InventoryModel> list, String type) {
        int size = list.size();

        // Start from the second element (index 1) as the first element is trivially sorted
        for (int i = 1; i < size; i++) {
            // Pick the element to be inserted
            InventoryModel key = list.get(i);
            int j = i - 1;

            // Move elements of list[0.i-1], that are greater than key.getAge(),
            // to one position ahead of their current position
            if (type.equals("ID")){
                while (j >= 0 && list.get(j).getProductID().compareTo(key.getProductID()) > 0) {
                    // Shift the current element to the right
                    list.set(j + 1, list.get(j));
                    j--;
                }
            }
            else if (type.equals("Name")){
                while (j >= 0 && list.get(j).getProductName().compareTo(key.getProductName()) > 0) {
                    // Shift the current element to the right
                    list.set(j + 1, list.get(j));
                    j--;
                }
            }
            else if (type.equals("Price")){
                while (j >= 0 && list.get(j).getProductPrice() > key.getProductPrice()) {
                    // Shift the current element to the right
                    list.set(j + 1, list.get(j));
                    j--;
                }
            }
            else if (type.equals("Quantity")){
                while (j >= 0 && list.get(j).getProductQuantity() > key.getProductQuantity()) {
                    // Shift the current element to the right
                    list.set(j + 1, list.get(j));
                    j--;
                }
            }
            else if (type.equals("Company")){
                while (j >= 0 && list.get(j).getProductCompany().compareTo(key.getProductCompany()) > 0) {
                    // Shift the current element to the right
                    list.set(j + 1, list.get(j));
                    j--;
                }
            } else if (type.equals("Date")){
                while (j >= 0 && list.get(j).getAddedDate().compareTo(key.getAddedDate()) > 0) {
                    // Shift the current element to the right
                    list.set(j + 1, list.get(j));
                    j--;
                }

            }

            // Place the key (the current element from the outer loop) in its correct position
            list.set(j + 1, key);
        }
    }

    public static void SelectionSrt(LinkedList<InventoryModel> list) {
        int size = list.size();

        for (int step = 0; step < size - 1; step++) {
            // Find the index of the minimum element in the remaining unsorted array
            int min_idx = step;

            for (int i = step + 1; i < size; i++) {
                // Compare names alphabetically using compareTo()
                // compareTo() returns a negative number if the first string comes before the second
                if (list.get(i).getProductID().compareToIgnoreCase(list.get(min_idx).getProductID()) < 0) {
                    min_idx = i; // Update the index to the new minimum
                }
            }

            // Swap the found minimum element with the element at the current step position
            InventoryModel temp = list.get(step);
            list.set(step, list.get(min_idx));
            list.set(min_idx, temp);
        }
    }



}
