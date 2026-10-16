# Merge k Sorted Lists

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given an array of `k` linked-lists `lists`, each linked-list is sorted in ascending order.

 *Merge all the linked-lists into one sorted linked-list and return it.* 

 

 **Example 1:** 

```
Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted linked list:
1->1->2->3->4->4->5->6

```

 **Example 2:** 

```
Input: lists = []
Output: []

```

 **Example 3:** 

```
Input: lists = [[]]
Output: []

```

 

 **Constraints:** 

- k == lists.length
- 0 <= k <= 104
- 0 <= lists[i].length <= 500
- -104 <= lists[i][j] <= 104
- lists[i] is sorted in ascending order.
- The sum of lists[i].length will not exceed 104.

## Solution

**Language:** Java  
**Runtime:** 5 ms (beats 42.77%)  
**Memory:** 46.3 MB (beats 95.32%)  
**Submitted:** 2026-10-16T16:03:21.879Z  

```java
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

```

---

[View on LeetCode](https://leetcode.com/problems/merge-k-sorted-lists/)