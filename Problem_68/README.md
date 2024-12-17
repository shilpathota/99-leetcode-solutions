# Same Tree

## Leet code Link - https://leetcode.com/problems/same-tree/description/

## complexity - Easy

## Description
Given the roots of two binary trees p and q, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.

 

#### Example 1:
<img src = "https://assets.leetcode.com/uploads/2020/12/20/ex1.jpg" />
```
Input: p = [1,2,3], q = [1,2,3]
Output: true
```
#### Example 2:
<img src = "https://assets.leetcode.com/uploads/2020/12/20/ex2.jpg" />
```
Input: p = [1,2], q = [1,null,2]
Output: false
```
#### Example 3:
<img src = "https://assets.leetcode.com/uploads/2020/12/20/ex3.jpg" />

```
Input: p = [1,2,1], q = [1,1,2]
Output: false
 ```

#### Constraints:
```
The number of nodes in both trees is in the range [0, 100].
-104 <= Node.val <= 104
```
---
## Solution

The solution is recursively comparing the values of the left and right of both the trees

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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null&&q==null) return true;
        if(p==null||q==null) return false;
        return (p.val==q.val)&&isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
    }
}
```
### Complexity
Time complexity : O(N),
where N is a number of nodes in the tree, since one visits
each node exactly once.

Space complexity : O(N) in the worst case of completely unbalanced tree, to keep a recursion stack.

this can also be done using iteration
```java
class Solution {
    public boolean check(TreeNode p, TreeNode q) {
        // p and q are null
        if (p == null && q == null) return true;
        // one of p and q is null
        if (q == null || p == null) return false;
        if (p.val != q.val) return false;
        return true;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (!check(p, q)) return false;

        // init deques
        ArrayDeque<TreeNode> deqP = new ArrayDeque<TreeNode>();
        ArrayDeque<TreeNode> deqQ = new ArrayDeque<TreeNode>();
        deqP.addLast(p);
        deqQ.addLast(q);

        while (!deqP.isEmpty()) {
            p = deqP.removeFirst();
            q = deqQ.removeFirst();

            if (!check(p, q)) return false;
            if (p != null) {
                // in Java nulls are not allowed in Deque
                if (!check(p.left, q.left)) return false;
                if (p.left != null) {
                    deqP.addLast(p.left);
                    deqQ.addLast(q.left);
                }
                if (!check(p.right, q.right)) return false;
                if (p.right != null) {
                    deqP.addLast(p.right);
                    deqQ.addLast(q.right);
                }
            }
        }
        return true;
    }
}
```
### complexity
Time complexity : O(N) since each node is visited
exactly once.

Space complexity : O(N) in the worst case, where the tree is a perfect fully balanced binary tree, since BFS will have to store at least an entire level of the tree in the queue, and the last level has O(N) nodes.

