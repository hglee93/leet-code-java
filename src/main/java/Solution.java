public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int answer = 0;

        for(int i = 0; i < 32; i++) {

            int temp = n & (1 << i);
            if(i < 16) {
                temp = temp << (31 - (i * 2));
            } else {
                temp = temp >>> ((i * 2) - 31);
            }
            answer = answer | temp;
        }

        return answer;
    }

    public int[] twoSum(int[] nums, int target) {

        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                if(target == (nums[i] + nums[j])) {
                    int[] answer = new int[2];
                    answer[0] = i;
                    answer[1] = j;
                    return answer;
                }
            }
        }

        return null;
    }

    public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode cursor = null;
        ListNode prevCursor = null;
        ListNode head = null;

        int carry = 0;

        while(!(l1 == null || l2 == null)) {

            int value = l1.val + l2.val + carry;

            carry = value / 10;

            cursor = new ListNode(value % 10);

            if(prevCursor != null) { prevCursor.next = cursor; }
            if(head == null) { head = cursor; }

            l1 = l1.next;
            l2 = l2.next;

            prevCursor = cursor;
            cursor = cursor.next;

        }

        if(l1 == null) {

            while(l2 != null) {

                int value = l2.val + carry;

                carry = value / 10;
                cursor = new ListNode(value % 10);

                if(prevCursor != null) { prevCursor.next = cursor; }

                if(head == null) { head = cursor; }

                l2 = l2.next;

                prevCursor = cursor;
                cursor = cursor.next;
            }

        } else if(l2 == null) {

            while(l1 != null) {
                int value = l1.val + carry;

                carry = value / 10;
                cursor = new ListNode(value % 10);

                if(prevCursor != null) { prevCursor.next = cursor; }

                if(head == null) { head = cursor; }

                l1 = l1.next;

                prevCursor = cursor;
                cursor = cursor.next;
            }

        }

        if(carry == 1) {
            cursor = new ListNode(1);
            if(prevCursor != null) { prevCursor.next = cursor; }
        }

        return head;
    }
}
