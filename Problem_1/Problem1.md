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
   Given the Linked list - Each linked list will have a Node where the element is present and the address of the next element<br/>
   The elements are already sorted in ascending order<br/>
2. Approach to Solution <br/>
   Iterate over the list from the head
   remove the repeated elements
   while returning the linked list it should point to the head.
<br/><b>Solution</b><br/>
To iterate over the list we can use while loop and the loop can continue till the head.next is not null. Also we have to verify if nead is not null to avoid iterating empty list

