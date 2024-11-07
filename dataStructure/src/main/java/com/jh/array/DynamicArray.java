package com.jh.array;

import java.util.Arrays;

/**
 * @author johnny
 */
public class DynamicArray {
    private int capacity = 10;
    private int size = 0;
    private int[] array;

    public DynamicArray(int capacity) {
        capacity = capacity;
        array = new int[capacity];
    }

    public void addLast(int element) {
        array[size] = element;
        size++;
    }

    public void add(int element, int position) {
        if (position >= 0 && position < size) {
            System.arraycopy(array, position, array, position + 1, size - position);
        }
        array[size] = element;
        size++;
    }

    public int get(int position) {
        return array[position];
    }

    public int getSize() {
        return size;
    }

    public int remove(int position) {
        if (position >= 0 && position < size) {
            int removedElement = array[position];
            System.arraycopy(array, position + 1, array, position, size - position -1);
            size--;
            return removedElement;
        }
        return -1;
    }

    public static void main(String[] args) {
        DynamicArray dynamicArray = new DynamicArray(10);
        dynamicArray.addLast(1);
        dynamicArray.addLast(2);
        dynamicArray.addLast(3);
        dynamicArray.addLast(4);
        dynamicArray.addLast(5);
        for (int i = 0; i < dynamicArray.getSize(); i++) {
            System.out.printf("%d ",dynamicArray.get(i));
        }
        System.out.println("--------------");
        dynamicArray.remove(2);
        for (int i = 0; i < dynamicArray.getSize(); i++) {
            System.out.printf("%d ",dynamicArray.get(i));
        }
    }
}
