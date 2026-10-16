import java.util.PriorityQueue;

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        // Initialize a Min-Heap based on the node values
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
        
        // Add the head of each non-empty linked list to the Min-Heap
        for (ListNode head : lists) {
            if (head != null) {
                pq.offer(head);
            }
        }
        
        // Dummy node acts as a starting anchor for the merged list
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        // Extract the minimum node and push its next element until the heap is empty
        while (!pq.isEmpty()) {
            ListNode smallestNode = pq.poll();
            current.next = smallestNode;
            current = current.next;
            
            // If the extracted node has a next element, push it into the heap
            if (smallestNode.next != null) {
                pq.offer(smallestNode.next);
            }
        }
        
        return dummy.next;
    }
}
