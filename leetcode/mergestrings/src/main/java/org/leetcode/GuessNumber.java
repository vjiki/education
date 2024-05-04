package org.leetcode;

import java.util.Random;

/**
 * Forward declaration of guess API.
 * @return 	     -1 if num is higher than the picked number
 *			      1 if num is lower than the picked number
 *               otherwise return 0
 * int guess(int num);
 */


public class GuessNumber {

    static int val = 9;
    public static int guess(int n) {
        if (n > val) {
            return -1;
        }
        if (n < val) {
            return 1;
        }
        return 0;
    }

    public static int guessNumber(int n) {

        int start = 1;
        int end = n;
        while (start <= end) {
            int mid = start + (end-start)/2;
            int res = guess(mid);
            if (res == 0) {
                return mid;
            }
            if (res > 0) {
                start = mid +1;
            } else {
                end = mid -1;
            }
        }
        return  -1;
    }
}
