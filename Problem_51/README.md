# Lowest Common Ancestor of a Binary Tree

## Leet code link - https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/

## Complexity - Medium

## Description
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

#### Example 1:
![image](https://github.com/user-attachments/assets/5dd463fe-372f-43ce-b431-66eacbf12f8d)

```plaintext
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
```

#### Example 2:
![image](https://github.com/user-attachments/assets/2c86b19d-0b31-4daa-bb5b-2e364d5e2d96)

```plaintext
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
```

#### Example 3:
```plaintext
Input: root = [1,2], p = 1, q = 2
Output: 1
```

---
## Solution

As the problem talks about finding the element and the ancestor, we can clearly use DFS algorithm.

#### Interpretation of the solution--
1. If the root is null I can return null which is the base case
2. If the root is equal to p or q which means p or q is at highest level and so it will be the output as the other one however follows.
3. We search the p and q in both left and right using recursion.
4. If present, both are returned means the left and right has two nodes p and q so the current root would be the common ancestor
5. If one is true and other is falese, which means p and q are in the current branch which is true so we can return whichever is true.

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */ 
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root!=null && (root ==p || root ==q)) return root;

        TreeNode lft=null;
         if(root.left!=null) lft=lowestCommonAncestor(root.left,p,q);
        TreeNode right=null;;
         if(root.right!=null) right= lowestCommonAncestor(root.right,p,q);

        if(lft!=null && right!=null) return root;
        else return lft==null?right:lft;
        
    }
}
```
### Complexity
Time Complexity: O(N), where N is the number of nodes in the binary tree. In the worst case we might be visiting all the nodes of the binary tree.

Space Complexity: O(N). This is because the maximum amount of space utilized by the recursion stack would be N since the height of a skewed binary tree could be N.

