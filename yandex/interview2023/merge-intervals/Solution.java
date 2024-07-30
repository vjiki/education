/*
* Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
*  and return an array of the non-overlapping intervals that cover all the intervals in the input.



Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
Example 2:

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.


Constraints:

1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 104
* */


import java.util.Arrays;
import java.util.Comparator;

class Solution {

  public static void main(String[] args) {

//    int[][] intervals = new int[][] {{1,3}, {2,6}, {8,10}, {15,18}};
    int[][] intervals = new int[][] {{4,5}, {1,5}};

    int[][] result = merge(intervals);
    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result[i].length; j++) {
        System.out.println(result[i][j]);
      }
    }

  }

  public static int[][] merge(int[][] intervals) {

    if (intervals.length <= 1) {
      return intervals;
    }

    Arrays.parallelSort(intervals, Comparator.comparing(a -> a[0]));

    int currentStartIndex = 0;
    int currentEndIndex = 1;
    int newArrayLength = intervals.length;

    for (int i = 0; i < intervals.length; i++) {
      if (currentEndIndex ==  intervals.length) {
        break;
      }
      int[] first = intervals[currentStartIndex];
      int[] second = intervals[currentEndIndex];
      int firstMax = first[1];
      int secondMin = second[0];

      if (secondMin <= firstMax ) {
        // overlapping
        int[] overlapInterval = new int[] {Math.min(first[0], second[0]), Math.max(first[1], second[1])};
        intervals[currentStartIndex] = null;
        intervals[currentEndIndex] = overlapInterval;
        newArrayLength--;
      }
      currentStartIndex++;
      currentEndIndex++;
    }

    int[][] result = new int[newArrayLength][2];
    int j = 0;
    for (int i = 0; i < intervals.length; i++) {
      if (intervals[i] != null) {
        result[j] = intervals[i];
        j++;
      }
    }

    return result;
  }
}