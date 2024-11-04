package com.jh.array;

import java.util.Arrays;

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
     *
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
                left = mid;
            }
        }
        if (array[left] == target) {
            return left;
        } else {
            return -1;
        }
    }

    /**
     * return the solution of inserted position when not exist  of Binary Search, which optimizing the comparing times in the loop when the target at the right of an array.
     * the time complexity is O(log(n))
     *
     * @param array  an ordered array
     * @param target the integer target value
     * @return the index of target in the array or -value(value is the inserted position) when not exist
     */
    public static int binarySearchWithInsertedPosition(int[] array, int target) {
        int left = 0, right = array.length;
        while (1 < right - left) {
            int mid = (left + right) >>> 1;
            if (target < array[mid]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (array[left] == target) {
            return left;
        } else {
            return -(left + 1); // target not found.
        }
    }

    /**
     * return the most left position of inserting the target value.
     *
     * @param array  an ordered array
     * @param target the integer target value
     * @return the most left index of value which >= target
     */
    public static int binarySearchLeftMost(int[] array, int target) {
        int left = 0, right = array.length - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (target <= array[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * return the most right position of inserting the target value.
     *
     * @param array  an ordered array
     * @param target the integer target value
     * @return the most right index of the value which <=target
     */
    public static int binarySearchRightMost(int[] array, int target) {
        int left = 0, right = array.length - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (target < array[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left - 1;
    }

    /**
     * Leetcode 35. Search Insert Position, use BinarySearch to implement it.
     *
     * @param nums
     * @param target
     * @return return the index if the target is found. If not, return the index where it would be if it were inserted in order.
     */
    public static int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (target < nums[mid]) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;
    }

    /**
     * Leetcode 35. Search Insert Position
     * use Left most BinarySearch to implement it.
     *
     * @param nums
     * @param target
     * @return return the index if the target is found. If not, return the index where it would be if it were inserted in order.
     */
    public static int searchInsertLeftMost(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (target <= nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }



    /**
     * Leetcode 34. Find First and Last Position of Element in Sorted Array
     * use Left most BinarySearch and the right most BinarySearch to implement it.
     *
     * @param nums
     * @param target
     * @return return the index if the target is found. If not, return the index where it would be if it were inserted in order.
     */
    public static int[] searchRange(int[] nums, int target) {
        int left = searchLeftMost(nums, target);
        if (left == -1){
            return new int[]{left, -1};
        }else{
            return new int[]{left, searchRightMost(nums, target)};
        }
    }
    public static int searchLeftMost(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int candidate = -1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (target < nums[mid]) {
                right = mid - 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else{
                candidate = mid;
                right = mid -1;
            }
        }
        return candidate;
    }

    public static int searchRightMost(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int candidate = -1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (target < nums[mid]) {
                right = mid - 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else{
                candidate = mid;
                left = mid + 1;
            }
        }
        return candidate;
    }
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 4, 4, 7, 8};
        int index = binarySearchBasic(array, 6);
        System.out.println(index);
        int index1 = binarySearchAdvanced(array, 6);
        System.out.println(index1);

        int leftMost = binarySearchLeftMost(array, 5);
        System.out.println(leftMost);

        int rightMost = binarySearchRightMost(array, 5);
        System.out.println(rightMost);

        //Input: nums = [1,3,5,6], target = 5
        //Output: 2
        int[] nums = {1, 3, 5, 6};
        System.out.println(searchInsert(nums, 2));

        System.out.println(searchInsertLeftMost(nums, 2));


        //Input: nums = [5,7,7,8,8,10], target = 8
        //Output: [3,4]
        int[] nums1 = {5,7,7,8,8,10};
        System.out.println(Arrays.toString(searchRange(nums1, 10)));
    }
}
