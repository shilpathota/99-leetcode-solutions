# Symmetric Tree

## Leet Code Link - https://leetcode.com/problems/symmetric-tree/description/

## Complexity - Easy

## Description
Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).

 

#### Example 1:
<img src = "https://assets.leetcode.com/uploads/2021/02/19/symtree1.jpg" />
```
Input: root = [1,2,2,3,4,4,3]
Output: true
```
#### Example 2:
<img src = "https://assets.leetcode.com/uploads/2021/02/19/symtree2.jpg" />
```
Input: root = [1,2,2,null,3,null,3]
Output: false
 ```

#### Constraints:
```
The number of nodes in the tree is in the range [1, 1000].
-100 <= Node.val <= 100
 ```

Follow up: Could you solve it both recursively and iteratively?

---
## Solution
WE know that the symmetric tree is the mirror of left and right. So we have to compare the values of both sides and then iteratively check the left and right 

Recursive solution 

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
    public boolean isMirror(TreeNode t1, TreeNode t2){
        if(t1==null && t2 == null) return true;
        if(t1==null||t2==null) return false;
        return (t1.val==t2.val)&& isMirror(t1.left,t2.right) && isMirror(t1.right,t2.left);
    }
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root,root);
    }
}
```
#### Complexity
Time complexity: O(n). Because we traverse the entire input tree once, the total run time is O(n), where n is the total number of nodes in the tree.

Space complexity: The number of recursive calls is bound by the height of the tree. In the worst case, the tree is linear and the height is in O(n). Therefore, space complexity due to recursive calls on the stack is O(n) in the worst case.

For iterative approach, we can use Queue and add root twice then compare it by poll it then add left of t1 and right of t2 so the iteration will compare them

```java
class Solution {
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }
}
```
Time complexity: O(n). Because we traverse the entire input tree once, the total run time is O(n), where n is the total number of nodes in the tree.

Space complexity: There is additional space required for the search queue. In the worst case, we have to insert O(n) nodes in the queue. Therefore, space complexity is O(n).
