# Count Complete Tree Nodes
### Leet Code Link - https://leetcode.com/problems/count-complete-tree-nodes/description/
### Complexity - Easy
## Description
Given the root of a complete binary tree, return the number of the nodes in the tree.

According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Design an algorithm that runs in less than O(n) time complexity.


**Example 1:** <br/>
Input: root = [1,2,3,4,5,6] <br/>
Output: 6 <br/>
**Example 2:** <br/>

Input: root = []<br/>
Output: 0<br/>
**Example 3:** <br/>

Input: root = [1]<br/>
Output: 1<br/>
 ## Solution
 We can use depth-first search algorithm to count the number of nodes where it recursively goes to each of the node.
