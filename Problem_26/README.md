# Diameter of BST

## Leet Code Link - https://leetcode.com/problems/diameter-of-binary-tree/description/

## Complexity - Easy

## Description

Given the root of a binary tree, return the length of the diameter of the tree.

The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

The length of a path between two nodes is represented by the number of edges between them.

#### Example 1:
![image](https://github.com/user-attachments/assets/1685de60-5400-42bd-a95f-1a907e2d3774)

```plaintext
Input: root = [1,2,3,4,5]
Output: 3
Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
```
#### Example 2:
```plaintext
Input: root = [1,2]
Output: 1
```
#### Constraints
```plaintext
The number of nodes in the tree is in the range [1, 104].
-100 <= Node.val <= 100
```
---
## Solution
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int diameter;
    int DFS(TreeNode root){
        if(root==null) return 0;
       int leftPath = DFS(root.left); //height of left subtree
       int rightPath = DFS(root.right); //height of right subtree

        diameter = Math.max(diameter,leftPath+rightPath); //update diameter
        return Math.max(leftPath,rightPath)+1; // return currrent subtree height
    }
    public int diameterOfBinaryTree(TreeNode root) {
        diameter = 0;
        DFS(root);
        return diameter;
    }
}
```
