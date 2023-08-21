package org.leetcode;

public class ArrayWithZeroes {

    public static void moveZeroesV0(int[] nums) {

        int lastIndex = nums.length;
        for (int firstPointer = 0; firstPointer < lastIndex; firstPointer++) {
            int secondPointer = firstPointer + 1;
            if (secondPointer > nums.length - 1) {
                break;
            }
            if (nums[firstPointer] == 0) {
                while (secondPointer < nums.length - 1 && nums[secondPointer] == 0) {
                    secondPointer++;
                }
                int tmp = nums[secondPointer];
                nums[secondPointer] = nums[firstPointer];
                nums[firstPointer] = tmp;
            }
        }
    }

    public static void moveZeroesV1(int[] nums) {

        int lastNonZeroElement = 0;
        for (int current = 0; current < nums.length; current++) {
            if (nums[current] != 0) {
                nums[lastNonZeroElement++] = nums[current];
            }
        }

        for (int i = lastNonZeroElement; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    private static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void moveZeroes(int[] nums) {
        int k = 0;
        int n = nums.length;
        for(int i=0;i<n;i++){
            if(nums[i] != 0){
                swap(nums,i,k);
                k++;
            }
        }
    }
}
