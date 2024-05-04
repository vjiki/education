package org.leetcode;

import java.util.Arrays;
import java.util.HashMap;

import static org.leetcode.GuessNumber.guessNumber;
import static org.leetcode.MergeLists.mergeKLists;

public class Main {



    public static void main(String[] args) {
        //System.out.println(MergeStrings.mergeAlternatelyV4("ab", "abc"));
        //System.out.println(MergeStrings.mergeAlternatelyV4("ab", "abc"));
        // 0,1,0,3,12
//        int[] arr = {0,1,0,3,12};
//        ArrayWithZeroes.moveZeroes(arr);
//        System.out.println(Arrays.toString(arr));

        // Input: lists = [[1,4,5],[1,3,4],[2,6]]
        //Output: [1,1,2,3,4,4,5,6]

//        System.out.println(guessNumber(3));

        System.out.println(Arrays.toString(twoSum(new int[]{3,2,4}, 6)));
    }




    public void testListNode() {
        ListNode a3 = new ListNode(5);
        ListNode a2 = new ListNode(4, a3);
        ListNode a1 = new ListNode(1, a2);

        ListNode b3 = new ListNode(4);
        ListNode b2 = new ListNode(3, b3);
        ListNode b1 = new ListNode(1, b2);

        ListNode c2 = new ListNode(6);
        ListNode c1 = new ListNode(2, c2);

        ListNode[] lists = {a1, b1, c1};

        for (ListNode curr : lists) {
            show(curr);
        }

        System.out.println("RESULT");

        ListNode res = mergeKLists(lists);
        show(res);
    }


    public static void show(ListNode curr) {
        while (curr.hasNext()) {
            System.out.println(curr.val);
            curr = curr.next;
        }
        System.out.println(curr.val);
    }

    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            int sub = target - nums[i];
            map.put(nums[i], i);
            if (map.containsKey(sub) && map.get(sub) != i) {
                return new int[] {i, map.get(sub)};
            }
        }
        for (int i = 0; i < nums.length; i++) {
            int sub = target - nums[i];
            if (map.containsKey(sub) && map.get(sub) != i) {
                return new int[] {i, map.get(sub)};
            }
        }
        return null;
    }

}