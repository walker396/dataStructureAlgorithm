package com.jh.array;

import java.util.Arrays;

public class BubbleSort {
    private void bubble(int[] array, int length) {
        if (length == 0) {
            return;
        }
        int lastSwapIndex = 0;
        for (int i = 0; i < length; i++) {
            if (array[i] > array[i + 1]) {
                int temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;
                lastSwapIndex = i;
            }
        }
        bubble(array, lastSwapIndex);
    }

    public void sort(int[] array){
        bubble(array, array.length-1);
    }

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        int[] arr = {4,1,2,4,5,3};
        bubbleSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
