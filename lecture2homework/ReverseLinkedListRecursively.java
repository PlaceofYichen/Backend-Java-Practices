package lecture2homework;

public class ReverseLinkedListRecursively {
    public ListNode reverseLinkedList(ListNode head) {
        // be careful about the base case,
        // need to make sure the later head.next.next != null
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseLinkedList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
// Time: O(n); Space: O(n)
