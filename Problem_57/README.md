# Lowest Common Ancestor of a Binary Tree II

## Leet code Link - https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/description/

## Complexity - Medium

## Description
Given the root of a binary tree, return the lowest common ancestor (LCA) of two given nodes, p and q. If either node p or q does not exist in the tree, return null. All values of the nodes in the tree are unique.

According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a binary tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)". A descendant of a node x is a node y that is on the path from node x to some leaf node.

 

#### Example 1:
![image](https://github.com/user-attachments/assets/9115e58a-5581-43d5-bb92-3423baae7ff5)

```plaintext
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
```
#### Example 2:
![image](https://github.com/user-attachments/assets/b130cb62-a7a4-4f53-bfce-844147bdd114)

```plaintext
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5. A node can be a descendant of itself according to the definition of LCA.
```
#### example 3:
![image](https://github.com/user-attachments/assets/9f6bddeb-ed3f-4bf0-8f0b-064ed3d3c39f)
```plaintext
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 10
Output: null
Explanation: Node 10 does not exist in the tree, so return null.
 ```

#### Constraints:
```plaintext
The number of nodes in the tree is in the range [1, 104].
-109 <= Node.val <= 109
All Node.val are unique.
p != q
```
---
## Solution
In the Lowest common ancestor problem we have a condition where p or q is guarenteed. So both of them exist in the tree. Hence we have the solution as after DFS we check if both are null or either is null and return non null values.

But here the problem is it can be null which means p or q can not exist. 

Initialization: Initialize a boolean flag nodesFound as False to keep track of whether both p and q are present in the tree.

Depth-First Search (DFS): Define a function dfs that takes a node as input and performs the following steps recursively:

Base case: If the current node is null, return null.
Recursive calls: Recursively call dfs on the left and right children of the current node, and store the results in left and right variables, respectively.
Check for presence: Check for each of the following conditions:

The current node is either p or q.
p or q is present in the left subtree (i.e., left is not null).
p or q is present in the right subtree (i.e., right is not null).
If any two of these conditions are true, set the nodesFound flag to True, indicating that both p and q are present in the tree.

Determine the lowest common ancestor: Check if either both left and right are non-null (indicating that p and q are present in different subtrees), or the current node is either p or q (indicating that we found atleast one node out of p and q). If either of these conditions is true, return the current node.

Return the result: After performing the DFS, store the result in the ans variable. If nodesFound is True, return ans as the lowest common ancestor; otherwise, return null.

```java
class Solution {
    boolean nodesFound = false;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ans = dfs(root, p, q);
        return nodesFound ? ans : null;
    }
    private TreeNode dfs(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null)
          return null;
        TreeNode left = dfs(node.left, p, q);
        TreeNode right = dfs(node.right, p, q);
        int conditions = 0;
        if (node == p || node == q)
          conditions++;
        if (left != null)
          conditions++;
        if (right != null)
          conditions++;
        if (conditions == 2)
          nodesFound = true;

        if ((left != null && right != null) || node == p || node == q) 
            return node;
        return left != null ? left : right;
    }
}
```
#### Complexity
Let N be the total number of nodes in the tree.

Time complexity: O(N). In the worst case we will go over the whole tree, for example when both p and q are not present in the tree.

Space complexity: O(N). This is the stack space used by the solution when performing depth first search over the tree. The max depth will be the height of the tree. In the worst case, height will be N when all the nodes form a chain.


there is one more solution where we already know the LCA solution if p and q are presetn. We can extend this solution and after getting this answer get the answer and check the other one in the same branch using dfs. If found return true. Else return null which will eliminate the edge case where one of them is not present in the tree
```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ans = LCA(root, p, q);
        if (ans == p) // check if q is in the subtree of p
            return dfs(p, q) ? p : null;
        else if (ans == q) // check if p is in the subtree of q
            return dfs(q, p) ? q : null;
        return ans; 
    }

    private TreeNode LCA(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null || node == p || node == q)
            return node;
        TreeNode left = LCA(node.left, p, q);
        TreeNode right = LCA(node.right, p, q);
        if (left != null && right != null)
            return node;
        else if (left != null)
            return left;
        else
            return right;
    }

    private boolean dfs(TreeNode node, TreeNode target) {
        if (node == target)
            return true;
        if (node == null)
            return false;
        return dfs(node.left, target) || dfs(node.right, target);
    }
}
```
#### Complexity 
Let N be the total number of nodes in the tree.

Time complexity: O(N). In the worst case we will go over the whole tree, for example when both p and q are not present in the tree.

Space complexity: O(N). This is the stack space used by the solution when performing depth first search over the tree. The max depth will be the height of the tree. In the worst case, height will be N when all the nodes form a chain.
