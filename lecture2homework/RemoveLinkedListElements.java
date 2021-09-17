package lecture2homework;

import java.util.List;

public class RemoveLinkedListElements {
    public ListNode removeElementsI(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        while (head != null) {
            if (head.value == val) {
                prev.next = head.next;
            } else {
                prev = head;
            }
            head = head.next;
        }
        return dummy.next;
    }
    // Time: O(n); Space: O(1)

    public ListNode removeElementsII(ListNode head, int val) {
        ListNode prev = null;
        ListNode newHead = null;
        while (head != null) {
            if (head.value == val) {
                if (prev != null) {
                    prev.next = head.next;
                }
            } else {
                if (newHead == null) {
                    newHead = head;
                }
                prev = head;
            }
            head = head.next;
        }
        return newHead;
    }
    // Time: O(n); Space: O(1)
}
