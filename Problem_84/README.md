# Reverse Linked List

## Leet Code Link - https://leetcode.com/problems/reverse-linked-list/description/

## Complexity - Easy

## Description
Given the head of a singly linked list, reverse the list, and return the reversed list.

#### Example 1:
<img src = "https://assets.leetcode.com/uploads/2021/02/19/rev1ex1.jpg" />

```
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]
```
#### Example 2:
<img src = "https://assets.leetcode.com/uploads/2021/02/19/rev1ex2.jpg" />

```
Input: head = [1,2]
Output: [2,1]
```
#### Example 3:
```
Input: head = []
Output: []
 ```

#### Constraints:
```
The number of nodes in the list is the range [0, 5000].
-5000 <= Node.val <= 5000
 ```
---
## Solution

The idea behind the reversing is 0 -> 1 -> 2 -> 3 can be reversed as 0 <- 1 <- 2 <- 3 which means we will have to iteratively change the next pointer for the variable by storing in some temp variable

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
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while(current!=null){
            ListNode temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        return prev;
        
    }
}
```
#### Complexity

Time complexity : O(n).

Assume that n is the list's length, the time complexity is O(n).

Space complexity : O(1).

Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?

But how can this be done recursively.

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
}
```
#### Complexity
Time complexity : O(n).

Assume that n is the list's length, the time complexity is O(n).

Space complexity : O(n).

The extra space comes from implicit stack space due to recursion. The recursion could go up to n levels deep.
