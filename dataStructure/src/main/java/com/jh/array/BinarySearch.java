package com.jh.array;

/**
 * the basic version of Binary Search
 *
 * @author Johnny
 */
public class BinarySearch {

    /**
     * the basic version of Binary Search
     *
     * @param array  an ordered array
     * @param target the integer target value
     * @return the index of target in the array or -1 when not exist
     */
    public static int binarySearchBasic(int[] array, int target) {
        int left = 0, right = array.length - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (target > array[mid]) {
                left = mid + 1;
            } else if (target < array[mid]) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * the advanced version of Binary Search, which optimizing the comparing times in the loop when the target at the right of an array.
     * the time complexity is O(log(n))
     * @param array  an ordered array
     * @param target the integer target value
     * @return the index of target in the array or -1 when not exist
     */
    public static int binarySearchAdvanced(int[] array, int target) {
        int left = 0, right = array.length;
        while (1 < right - left) {
            int mid = (left + right) >>> 1;
            if (target < array[mid]) {
                right = mid;
            } else {
                left =  mid;
            }
        }
        if (array[left] == target) {
            return left;
        } else {
            return -1;
        }
    }


    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        int index = binarySearchBasic(array, 6);
        System.out.println(index);
        int index1 = binarySearchAdvanced(array, 6);
        System.out.println(index1);

    }
}
