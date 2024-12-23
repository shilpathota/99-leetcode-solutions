# Merge Two Sorted Lists

## Leet Code Link - https://leetcode.com/problems/merge-two-sorted-lists/description/

## Complexity - Easy

## Description
You are given the heads of two sorted linked lists list1 and list2.

Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.

Return the head of the merged linked list.

 

#### Example 1:
<img src="https://assets.leetcode.com/uploads/2020/10/03/merge_ex1.jpg" />

```
Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]
```
#### Example 2:
```
Input: list1 = [], list2 = []
Output: []
```
#### Example 3:
```
Input: list1 = [], list2 = [0]
Output: [0]
 ```

#### Constraints:
```
The number of nodes in both lists is in the range [0, 50].
-100 <= Node.val <= 100
Both list1 and list2 are sorted in non-decreasing order.
```
---
## Solution
AS this is linked list, we can use 2 approaches where iteratively add the elements by changing the next pointers. or recursively do this.

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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode prehead = new ListNode(-1);

        ListNode head = prehead;
        while(list1!=null && list2!=null){
            if(list1.val<list2.val){
                head.next = list1;
                list1 = list1.next;
            }
            else{
                head.next  = list2;
                list2 = list2.next;
            }
            head = head.next;
        }
        if(list1!=null){
            head.next = list1;
        }
        if(list2!=null){
            head.next = list2;
        }
        return prehead.next;
    }
}
```
#### Complexity 
Time complexity : O(n+m)

Because exactly one of l1 and l2 is incremented on each loop
iteration, the while loop runs for a number of iterations equal to the
sum of the lengths of the two lists. All other work is constant, so the
overall complexity is linear.

Space complexity : O(1)

The iterative approach only allocates a few pointers, so it has a
constant overall memory footprint.

Recursive approach

```java
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
```
Time complexity : O(n+m)

Because each recursive call increments the pointer to l1 or l2 by one (approaching the dangling null at the end of each list), there will be exactly one call to mergeTwoLists per element in each list. Therefore, the time complexity is linear in the combined size of the lists.

Space complexity : O(n+m)

The first call to mergeTwoLists does not return until the ends of both l1 and l2 have been reached, so n+m stack frames consume O(n+m) space.
