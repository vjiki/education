package org.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Candies {

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = Arrays.stream(candies).filter(candy -> candy >= 0).max().orElse(0);
        return Arrays.stream(candies).mapToObj(candy -> (candy + extraCandies) >= max).collect(Collectors.toList());
    }

    public List<Boolean> kidsWithCandiesV1(int[] candies, int extraCandies) {
        int max = Arrays.stream(candies).max().orElse(0);
        List<Boolean> candiesBoolean = new ArrayList<>();
        for (int candy : candies) {
            candiesBoolean.add((candy + extraCandies) >= max);
        }
        return candiesBoolean;
    }

    public List<Boolean> kidsWithCandiesV2(int[] candies, int extraCandies) {
        //Arrays.stream(candies).sorted();
//        int max = Arrays.stream(candies).max().orElse(0);
        int max = max(candies);
        List<Boolean> candiesBoolean = new ArrayList<>();
        for (int candy : candies) {
            candiesBoolean.add((candy + extraCandies) >= max);
        }
        return candiesBoolean;
    }

    public List<Boolean> kidsWithCandiesV3(int[] candies, int extraCandies) {
        // Find out the greatest number of candies among all the kids.
        int maxCandies = 0;
        for (int candy : candies) {
            maxCandies = Math.max(candy, maxCandies);
        }
        // For each kid, check if they will have greatest number of candies
        // among all the kids.
        List<Boolean> result = new ArrayList<>();
        for (int candy : candies) {
            result.add(candy + extraCandies >= maxCandies);
        }

        return result;
    }

    int max (int[] arr) {
        int max1 = 0;
        int max2 = 0;

        if (arr.length%2 == 0) {
            for (int i = 0; i < arr.length / 2; i++) {
                if (arr[i] >= max1) {
                    max1 = arr[i];
                }
                if (arr[arr.length / 2 + i] >= max2) {
                    max2 = arr[arr.length / 2 + i];
                }
            }
        } else {
            for (int j : arr) {
                if (j >= max1) {
                    max1 = j;
                }
            }
        }
        return Math.max(max1, max2);
    }

}
