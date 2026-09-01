/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 *     }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Create a dummy node to seamlessly handle edge cases like removing the head
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode fast = dummy;
        ListNode slow = dummy;
        
        // Move the fast pointer n steps ahead to establish the target gap
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        
        // Advance both pointers until fast reaches the final node
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        
        // Slow is now right before the target node; update its next reference to skip it
        slow.next = slow.next.next;
        
        // Return the modified list starting from the original head
        return dummy.next;
    }
}
