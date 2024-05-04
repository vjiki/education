package org.leetcode;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class MergeLists {

        public static ListNode mergeKLists(ListNode[] lists) {
            TreeMap<Integer, Integer> map = new TreeMap(Collections.reverseOrder());

            for (ListNode currListNode : lists) {
                while (currListNode.next != null) {
                    if (map.containsKey(currListNode.val)) {
                        int newVal = map.get(currListNode.val) + 1;
                        map.put(currListNode.val, newVal);
                    } else {
                        map.put(currListNode.val, 1);
                    }
                    currListNode = currListNode.next;
                }
                if (map.containsKey(currListNode.val)) {
                    int newVal = map.get(currListNode.val) + 1;
                    map.put(currListNode.val, newVal);
                } else {
                    map.put(currListNode.val, 1);
                }
            }

            if (map.isEmpty()) {
                return new ListNode();
            }
            ListNode last = new ListNode(Integer.MIN_VALUE);
            ListNode res = new ListNode();
            for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
                for (int i = 0; i < entry.getValue(); i++) {
                    if (last.val == Integer.MIN_VALUE) {
                        res = new ListNode(entry.getKey());
                    } else {
                        res = new ListNode(entry.getKey(), last);
                    }
                    last = res;
                }
            }

            return res;
        }



    public ListNode mergeKListsV2(ListNode[] lists) {
        HashMap<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(ListNode head : lists) {
            while(head != null) {
                pq.add(head.val);
                map.put(head.val, map.getOrDefault(head.val, 0) + 1);
                head = head.next;
            }
        }

        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        while(!pq.isEmpty()) {
            int val = pq.poll();
            ListNode newNode = new ListNode(val);
            current.next = newNode;
            current = current.next;

            Integer count = map.get(val);
            if (count != null && count > 0) {
                map.put(val, count - 1);
            } else {
                map.remove(val);
            }


        }

        return dummy.next;
    }

    public ListNode mergeKListsV3_Best(ListNode[] lists) {
        if (lists==null||lists.length==0) return null;

        PriorityQueue<ListNode> queue= new PriorityQueue<ListNode>(lists.length,new Comparator<ListNode>(){
            @Override
            public int compare(ListNode o1,ListNode o2){
                if (o1.val<o2.val)
                    return -1;
                else if (o1.val==o2.val)
                    return 0;
                else
                    return 1;
            }
        });

        ListNode dummy = new ListNode(0);
        ListNode tail=dummy;

        for (ListNode node:lists)
            if (node!=null)
                queue.add(node);

        while (!queue.isEmpty()){
            tail.next=queue.poll();
            tail=tail.next;

            if (tail.next!=null)
                queue.add(tail.next);
        }
        return dummy.next;
    }


    public ListNode mergeKLists_v4(ListNode[] lists) {
        if(lists.length == 0)
            return null;
        ListNode merged = mergeK(lists, 0, lists.length - 1);
        return merged;
    }

    static ListNode mergeK(ListNode[] lists, int i, int j) {
        if(j == i)
            return lists[i];
        if(j - i == 1)
            return mergeTwo(lists[i], lists[j]);

        int mid = i + (j - i)/2;
        return mergeTwo(mergeK(lists, i, mid), mergeK(lists, mid + 1, j));
    }

    static ListNode mergeTwo(ListNode list1, ListNode list2){
        if(list1 == null)
            return list2;
        if(list2 == null)
            return list1;

        if(list1.val < list2.val) {
            list1.next = mergeTwo(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwo(list1, list2.next);
            return list2;
        }
    }
}
