public class TwoIntegersSumV2 {


    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        StringBuilder firstRow = new StringBuilder();
        StringBuilder secondRow = new StringBuilder();
        while (l1 != null) {
            firstRow.append(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            secondRow.append(l2.val);
            l2 = l2.next;
        }

        char[] elems1 = firstRow.reverse().toString().toCharArray();
        char[] elems2 = secondRow.reverse().toString().toCharArray();
        int headVal = Character.getNumericValue(elems1[0]) + Character.getNumericValue(elems2[0]);
        int addition = (headVal % 10) == 0 ? 1 : 0;
        ListNode head = new ListNode(headVal % 10);
        ListNode cur = head;
        int maxLength = Math.max(elems1.length, elems2.length);
        for (int i = 1; i < maxLength; i++) {
            char firstElem = 48;
            char secondElem = 48;
            if (i < elems1.length) {
                firstElem = elems1[i];
            }
            if (i < elems2.length) {
                secondElem = elems2[i];
            }
            int newElem = Character.getNumericValue(firstElem) + Character.getNumericValue(secondElem) + addition;
            addition = (newElem % 10) == 0 ? 1 : 0;
            ListNode newNode = new ListNode(newElem % 10);
            cur.next = newNode;
            cur = newNode;
        }

        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    public static void main(String[] args) {
 // Input: l1 = [2,4,3], l2 = [5,6,4]
        //Output: [7,0,8]
        //Explanation: 342 + 465 = 807.

        ListNode l1 = new ListNode(2);
        ListNode next1 = new ListNode(4);
        l1.next = next1;
        next1.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        ListNode next2 = new ListNode(6);
        l2.next = next2;
        next2.next = new ListNode(4);

        System.out.println(addTwoNumbers(l1, l2).val);
    }
}
