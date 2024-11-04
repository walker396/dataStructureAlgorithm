package com.jh.array;

/**
 * the basic version of Binary Search
 * @author Johnny
 */
public class BinarySearch {

    /**
     * the basic version of Binary Search
     * @param array  an ordered array
     * @param target the integer target value
     * @return the index of target in the array or -1 when not exist
     */
    public static int binarySearchBasic(int[] array, int target){
        int left = 0, right = array.length -1 ;
        while(left <= right){
            int mid = (left + right) >>>1;
            if(target > array[mid]) {
                left = mid + 1;
            } else if (target < array[mid]){
                right = mid - 1;
            } else{
                return mid;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7};
        int index = binarySearchBasic(array, 8);
        System.out.println(index);

    }
}
