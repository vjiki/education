package org.leetcode;

import java.util.Arrays;

public class Main {



    public static void main(String[] args) {
        //System.out.println(MergeStrings.mergeAlternatelyV4("ab", "abc"));
        //System.out.println(MergeStrings.mergeAlternatelyV4("ab", "abc"));
        // 0,1,0,3,12
        int[] arr = {0,1,0,3,12};
        ArrayWithZeroes.moveZeroes(arr);
        System.out.println(Arrays.toString(arr));

    }
}