**Link to Leetcode -** https://leetcode.com/problems/remove-duplicates-from-sorted-list/ <br/>
<b>Complexity - </b>   <span style="color:green">Easy</span><br/>
<b>Question - </b> <br/>
Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.



<b>Example 1:</b>

<img src = "https://assets.leetcode.com/uploads/2021/01/04/list1.jpg"/><br/>
Input: head = [1,1,2]<br/>
Output: [1,2]<br/>
<b>Example 2:</b>

 <img src="https://assets.leetcode.com/uploads/2021/01/04/list2.jpg"/><br/>
Input: head = [1,1,2,3,3]<br/>
Output: [1,2,3]
 
<br/>
<b>Constraints:</b>

The number of nodes in the list is in the range [0, 300].<br/>
-100 <= Node.val <= 100<br/>
The list is guaranteed to be sorted in ascending order.<br/>
<b>Inference</b><br/>
1. Understanding the Problem<br/>
   a. Given the Linked list - Each linked list will have a Node where the element is present and the address of the next element.<br/>
   <img src="https://media.geeksforgeeks.org/wp-content/cdn-uploads/gq/2013/03/Linkedlist.png"/><br/>
   b. The elements are already sorted in ascending order.<br/>
2. Approach to Solution <br/>
   a. Iterate over the list from the head.<br/>
   b. Remove the repeated elements.<br/>
   c. While returning the linked list it should point to the head.<br/>


<b>Solution</b><br/>
1. To iterate over the list from head - For this, we can use while loop and the loop can continue till the head.next (Address of next element) is not null.<br/>
2. Also we have to verify if head is not null to avoid iterating empty list.<br/>
3. Validate if the next element is same as the current head.<br/>
4. Replace the next element with next.next element so that one element in between which is same as current element is skipped.<br/>
5. If the head is not same as next then no action is performed.<br/>
6. Repeat this process until all the elements are completed and we come out of while loop.<br/>
7. As we have to return head of the final linked list we store it at the beginning in other variable so the variable can be returned.<br/>

<img src="https://github.com/shilpathota/99-leetcode-solutions/blob/main/Problem_1/Problem1.drawio.png"/>
<br/>
Now that we got the right solution, let's analyze the complexity<br/>
<b>Time Complexity -</b><br/>
As we have to iterate all the elements atleast once, if we have n elements, it should take O(n) as time complexity.<br/>
<b>Space Complexity - </b><br/>
We are using one variable to store head and so the Space is O(1).<br/>
Now that we got all the analysis done, let's jump into the coding!!
<br/>


For Java Solution - <a href="https://github.com/shilpathota/99-leetcode-solutions/blob/main/Problem_1/Problem_1.java">Problem_1.java</a><br/>
For Python Solution - <a href="https://github.com/shilpathota/99-leetcode-solutions/blob/main/Problem_1/Problem_1.py">Problem_1.py</a><br/>
For Javascript Solution - <a href="https://github.com/shilpathota/99-leetcode-solutions/blob/main/Problem_1/Problem_1.js">Problem_1.js</a><br/>
For Typescript Solution - <a href="https://github.com/shilpathota/99-leetcode-solutions/blob/main/Problem_1/Problem_1.ts">Problem_1.ts</a><br/>
For C# Solution - <a href="https://github.com/shilpathota/99-leetcode-solutions/blob/main/Problem_1/Problem_1.cs">Problem_1.cs</a><br/>

## Solution

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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode root=head;
        while(head!=null){
            while(head.next!=null && head.val==head.next.val){
                head.next=head.next.next!=null?head.next.next:null;
            }
            head=head.next;
        }
        return root;
    }
}
```
