# Closest Binary Search Tree Value

## Leet code Link - https://leetcode.com/problems/closest-binary-search-tree-value/description/

## Complexity - Easy

## Description
Given the root of a binary search tree and a target value, return the value in the BST that is closest to the target. If there are multiple answers, print the smallest.

 

#### Example 1:
<img src ="https://assets.leetcode.com/uploads/2021/03/12/closest1-1-tree.jpg" />

```
Input: root = [4,2,5,1,3], target = 3.714286
Output: 4
```
#### Example 2:
```
Input: root = [1], target = 4.428571
Output: 1
```

#### Constraints:
```
The number of nodes in the tree is in the range [1, 104].
0 <= Node.val <= 109
-109 <= target <= 109
```

---
## Solution
As this is BST we can compare the target and go right or left based on the value as BST has all left values are less than it and right values are greater than it.

As brute force approach, we can use inorder traversal to get the ascending order of values and locate the value closest to it based on the target
```java
class Solution {
  public void inorder(TreeNode root, List<Integer> nums) {
    if (root == null) return;
    inorder(root.left, nums);
    nums.add(root.val);
    inorder(root.right, nums);
  }

  public int closestValue(TreeNode root, double target) {
    List<Integer> nums = new ArrayList();
    inorder(root, nums);
    return Collections.min(nums, new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return Math.abs(o1 - target) < Math.abs(o2 - target) ? -1 : 1;
      }
    });
  }
}
```
Time complexity: O(N) because to build inorder traversal and then to perform linear search takes linear time.

Space complexity: O(N) to keep inorder traversal.

We can use stack for this as well

Initiate stack as an empty array and predecessor value as a very small number.

While root is not null:

To build an inorder traversal iteratively, go left as far as you can and add all nodes on the way into the stack.

Pop the last element from stack root = stack.pop().

If the target is in-between of pred and root.val, return the closest between these two elements.

Set predecessor value to be equal to root.val and go one step right: root = root.right.

We're here because during the loop one couldn't identify the closest value. That means that the closest value is the last value in the inorder traversal, i.e. current predecessor value. Return it.

```java
class Solution {
  public int closestValue(TreeNode root, double target) {
    LinkedList<TreeNode> stack = new LinkedList();
    long pred = Long.MIN_VALUE;

    while (!stack.isEmpty() || root != null) {
      while (root != null) {
        stack.add(root);
        root = root.left;
      }
      root = stack.removeLast();

      if (pred <= target && target < root.val)
        return Math.abs(pred - target) <= Math.abs(root.val - target) ? (int)pred : root.val;

      pred = root.val;
      root = root.right;
    }
    return (int)pred;
  }
}
```
Time complexity: O(k) in the average case and O(H+k) in the worst case, where k is an index of the closest element. It's known that the average case is a balanced tree, in that case stack always contains a few elements, and hence one does 2k operations to go to kth element in inorder traversal (k times to push into stack and then k times to pop out of stack). That results in O(k) time complexity. The worst case is a completely unbalanced tree, where you first push H elements into the stack and then pop out k elements, which results in O(H+k) time complexity.

Space complexity: up to O(H) to keep the stack in the case of a non-balanced tree.

Approach 2 works fine when indexing k of the closest element is much smaller than the tree height H.

Let's now consider another limit and optimize Approach 1 in the case of relatively large k, comparable with N.

Then it makes sense to use a binary search: go left if the target is smaller than the current root value, and go right otherwise. Choose the closest to the target value at each step.

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
    public int closestValue(TreeNode root, double target) {
        int val;
        int closest =root.val;
        while (root!=null){
            val= root.val;
            closest = (Math.abs(val-target)<Math.abs(closest-target) || (Math.abs(val-target)==Math.abs(closest-target))&& val<closest)?val:closest;
            root=target<root.val?root.left:root.right;
        }
        return closest;
    }
}
```
#### Complexity 
Time complexity : O(H) since here one goes from root down to a leaf.

Space complexity : O(1).

