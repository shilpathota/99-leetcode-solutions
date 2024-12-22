# Odd Even Linked List

## Leet Code Link - https://leetcode.com/problems/odd-even-linked-list/description/

## Complexity - Medium


## Description
Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.

The first node is considered odd, and the second node is even, and so on.

Note that the relative order inside both the even and odd groups should remain as it was in the input.

You must solve the problem in O(1) extra space complexity and O(n) time complexity.

#### Example 1:
<img src="https://assets.leetcode.com/uploads/2021/03/10/oddeven-linked-list.jpg" />

```
Input: head = [1,2,3,4,5]
Output: [1,3,5,2,4]
```
#### Example 2:
<img src ="https://assets.leetcode.com/uploads/2021/03/10/oddeven2-linked-list.jpg" />

```
Input: head = [2,1,3,5,6,4,7]
Output: [2,3,6,7,1,5,4]
 ```

#### Constraints:
```
The number of nodes in the linked list is in the range [0, 104].
-106 <= Node.val <= 106
```
---
## Solution
A well-formed LinkedList need two pointers head and tail to support operations at both ends. The variables head and odd are the head pointer and tail pointer of one LinkedList we call oddList; the variables evenHead and even are the head pointer and tail pointer of another LinkedList we call evenList. The algorithm traverses the original LinkedList and put the odd nodes into the oddList and the even nodes into the evenList. To traverse a LinkedList we need at least one pointer as an iterator for the current node. But here the pointers odd and even not only serve as the tail pointers but also act as the iterators of the original list.

```java
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
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if(head==null||head.next==null) return head;
        ListNode odd = head;
        ListNode even = head.next,evenHead = even;
        while(even != null&& even.next!=null){
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
```
#### Complexity
Complexity Analysis

Time complexity : O(n). There are total n nodes and we visit each node once.

Space complexity : O(1). All we need is the four pointers.
