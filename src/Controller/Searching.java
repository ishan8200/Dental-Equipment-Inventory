/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.InventoryModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Ripple Device
 */
public class Searching {

    Sorting sort = new Sorting();

    public static int BinarySearchingID (LinkedList<InventoryModel> list, String target){
        int left=0;
        int right=list.size()-1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = list.get(mid).getProductID().compareToIgnoreCase(target);

            if (comparison == 0) {
                System.out.println("Product found at index: " + mid);
                return mid;
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }
        System.out.println("Product not found.");
        return -1;
    }

    public static int BinarySearchingName (LinkedList<InventoryModel> list, String target){
        int left=0;
        int right=list.size()-1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = list.get(mid).getProductName().compareToIgnoreCase(target);

            if (comparison == 0) {
                System.out.println("Product found at index: " + mid);
                return mid;
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println("Product not found.");
        return -1;
    }

    public static int BinarySearchingCompany (LinkedList<InventoryModel> list, String target){
        int left=0;
        int right=list.size()-1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = list.get(mid).getProductCompany().compareToIgnoreCase(target);

            if (comparison == 0) {
                System.out.println("Product found at index: " + mid);
                return mid;
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println("Product not found.");
        return -1;
    }

    public static List<Integer> BinarySearchingCompanyName(LinkedList<InventoryModel> list, String target) {
        List<Integer> results = new ArrayList<>();

        // First, find ANY occurrence using binary search
        int index = BinarySearchingCompany(list, target);
        results.add(index);

        if (index == -1) {
            System.out.println("Product not found.");
            return results;
        }

        // From the found index, scan left for more occurrences
        int left = index;
        while (left >= 0 && list.get(left).getProductCompany().equalsIgnoreCase(target)) {
            results.add(left);
            left--;
        }

        // Scan right for more occurrences
        int right = index + 1;
        while (right < list.size() && list.get(right).getProductCompany().equalsIgnoreCase(target)) {
            results.add(right);
            right++;
        }

        System.out.println("Found " + results.size() + " products named: " + target);
        return results;
    }


    public static void mergeSort(LinkedList<InventoryModel> list) {
        if (list.size() <= 1) {
            return; // Base case: list is already sorted
        }

        // Create a temporary list for merging
        LinkedList<InventoryModel> temp = new LinkedList<>(list);
        mergeSort(list, temp, 0, list.size() - 1);
    }

    private static void mergeSort(LinkedList<InventoryModel> list,
                                  LinkedList<InventoryModel> temp,
                                  int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            // Recursively sort left and right halves
            mergeSort(list, temp, left, mid);
            mergeSort(list, temp, mid + 1, right);

            // Merge the sorted halves
            merge(list, temp, left, mid, right);
        }
    }

    private static void merge(LinkedList<InventoryModel> list,
                              LinkedList<InventoryModel> temp,
                              int left, int mid, int right) {
        // Copy the segment to temp list
        for (int i = left; i <= right; i++) {
            temp.set(i, list.get(i));
        }

        int i = left;      // Pointer for left subarray
        int j = mid + 1;   // Pointer for right subarray
        int k = left;      // Pointer for merged array

        while (i <= mid && j <= right) {
            // Compare ProductID (case-insensitive)
            String leftID = temp.get(i).getProductID();
            String rightID = temp.get(j).getProductID();

            if (leftID.compareToIgnoreCase(rightID) <= 0) {
                list.set(k++, temp.get(i++));
            } else {
                list.set(k++, temp.get(j++));
            }
        }

        // Copy remaining elements from left subarray
        while (i <= mid) {
            list.set(k++, temp.get(i++));
        }
    }

    
}
