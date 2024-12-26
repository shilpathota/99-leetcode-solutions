# Add Two Numbers

## Leet code link - https://leetcode.com/problems/add-two-numbers/description/

## Complexity - Medium

## Description
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 

#### Example 1:
<img src="https://assets.leetcode.com/uploads/2020/10/02/addtwonumber1.jpg" />

```
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.
```
#### Example 2:
```
Input: l1 = [0], l2 = [0]
Output: [0]
```
#### Example 3:
```
Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]
 ```

#### Constraints:
```
The number of nodes in each linked list is in the range [1, 100].
0 <= Node.val <= 9
It is guaranteed that the list represents a number that does not have leading zeros.
```

---
## solution
Just like how you would sum two numbers on a piece of paper, we begin by summing the least-significant digits, which is the head of l1 and l2. Since each digit is in the range of 0…9, summing two digits may "overflow". For example 5+7=12. In this case, we set the current digit to 2 and bring over the carry=1 to the next iteration. carry must be either 0 or 1 because the largest possible sum of two digits (including the carry) is 9+9+1=19.

The pseudocode is as following:

- Initialize current node to dummy head of the returning list.
- Initialize carry to 0.
- Loop through lists l1 and l2 until you reach both ends and carry is 0.
- Set x to node l1's value. If l1 has reached the end of l1, set to 0.
- Set y to node l2's value. If l2 has reached the end of l2, set to 0.
- Set sum=x+y+carry.
- Update carry=sum/10.
- Create a new node with the digit value of (summod10) and set it to current node's next, then advance current node to next.
- Advance both l1 and l2.
- Return dummy head's next node.
Note that we use a dummy head to simplify the code. Without a dummy head, you would have to write extra conditional statements to initialize the head's value.

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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
                ListNode dummy = new ListNode(-1);
                int carry =0;
                ListNode newnode =dummy;
            while(l1!=null || l2 !=null || carry!=0 ){
                int x = l1!=null ? l1.val : 0;
                int y = l2!=null ? l2.val : 0;
                int sum = carry + x+ y;
                carry = sum/10;
                newnode.next = new ListNode(sum%10);
                newnode = newnode.next;
                if(l1!=null) l1=l1.next;
                if(l2!=null) l2=l2.next;
            }
        return dummy.next;
    }
}
```
#### Complexity
Time complexity : O(max(m,n)). Assume that m and n represents the length of l1 and l2 respectively, the algorithm above iterates at most max(m,n) times.

Space complexity : O(1). The length of the new list is at most max(m,n)+1 However, we don't count the answer as part of the space complexity.
