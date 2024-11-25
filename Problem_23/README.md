# 938. Range Sum of BST

## Leet Code Link - https://leetcode.com/problems/range-sum-of-bst/description/

## Complexity - Easy
---
## Description

Given the root node of a binary search tree and two integers low and high, return the sum of values of all nodes with a value in the inclusive range `[low, high]`.

#### Example 1:
![image](https://github.com/user-attachments/assets/8c777b06-21a7-42ec-b3ae-7c7a09e614b4)

```plaintext
Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
Output: 32
Explanation: Nodes 7, 10, and 15 are in the range [7, 15]. 7 + 10 + 15 = 32.
```
#### Example 2:
![image](https://github.com/user-attachments/assets/c65d37e4-69d0-44d0-9e84-209d27288392)

```plaintext
Input: root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
Output: 23
Explanation: Nodes 6, 7, and 10 are in the range [6, 10]. 6 + 7 + 10 = 23.
```

#### Constraints:

The number of nodes in the tree is in the range `[1, 2 * 104]`.

`1 <= Node.val <= 105`

`1 <= low <= high <= 105`

## Solution

Here we would like to get the elemenets in inorder traversal or can use DFS algorithm. 

In both cases, we recursively fetch the elements and add them if the condition matches

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
    public int inorderTraversal(TreeNode root,int low,int high,int sum){
        if(root==null) return sum;

        sum=inorderTraversal(root.left, low, high, sum);
        if(root.val>=low && root.val <= high){
            sum= sum+root.val;
        }
        if(root.val>high){
            return sum;
        }
        sum = inorderTraversal(root.right, low, high, sum);
        return sum;
    }
    public int rangeSumBST(TreeNode root, int low, int high) {
        int sum=0;
        sum = inorderTraversal(root, low, high, sum);
        return sum;
    }
}
```
This is my solution

We also have DFS solution

```java

class Solution {
    int ans;
    public int rangeSumBST(TreeNode root, int low, int high) {
        ans = 0;
        dfs(root, low, high);
        return ans;
    }

    public void dfs(TreeNode node, int low, int high) {
        if (node != null) {
            if (low <= node.val && node.val <= high)
                ans += node.val;
            if (low < node.val)
                dfs(node.left, low, high);
            if (node.val < high)
                dfs(node.right, low, high);
        }
    }
}
```

We can also use stack for this

``` java
class Solution {
    public int rangeSumBST(TreeNode root, int low, int high) {
        int ans = 0;
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                if (low <= node.val && node.val <= high)
                    ans += node.val;
                if (low < node.val)
                    stack.push(node.left);
                if (node.val < high)
                    stack.push(node.right);
            }
        }
        return ans;
    }
}
```




