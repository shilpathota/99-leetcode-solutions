# Binary Tree Inorder Traversal

## Link - https://leetcode.com/problems/binary-tree-inorder-traversal/description/

## Complexity - Easy

Given the root of a binary tree, return the inorder traversal of its nodes' values.

 

#### Example 1:

<img src = "https://assets.leetcode.com/uploads/2020/09/15/inorder_1.jpg"/>
```
Input: root = [1,null,2,3]
Output: [1,3,2]
```
#### Example 2:
```
Input: root = []
Output: []
```
#### Example 3:
```
Input: root = [1]
Output: [1]
 ```

#### Constraints:
```
The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
 ```
---
## Solution
This is the basic traversal of binary tree which is inorder traversal where we visit the left then root and then right

this can be done using recursion

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
        List<Integer> out = new ArrayList<>();
        public void inorder(TreeNode node){
            if(node == null) return;
            inorderTraversal(node.left);
            out.add(node.val);
            inorderTraversal(node.right);
        }
    public List<Integer> inorderTraversal(TreeNode root) {
        inorder(root);
        return out;
    }
}
```


