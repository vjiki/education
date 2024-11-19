public class TwoIntegersSum {


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

        long firstVal = Long.parseLong(firstRow.reverse().toString());
        long secondVal = Long.parseLong(secondRow.reverse().toString());
        long res = firstVal + secondVal;

        StringBuilder builder = new StringBuilder();
        builder.append(res);
        builder.reverse();

        char[] elems = builder.toString().toCharArray();
        ListNode head = new ListNode(Character.getNumericValue(elems[0]));
        ListNode cur = head;
        for (int i = 1; i < elems.length; i++) {
            ListNode newNode = new ListNode(Character.getNumericValue(elems[i]));
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
