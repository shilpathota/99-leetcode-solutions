# Binary Tree Right Side View

## Leet Code Link - https://leetcode.com/problems/binary-tree-right-side-view/description/

## Complexity - Medium

## Description

Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

#### Example 1:
```plaintext
Input: root = [1,2,3,null,5,null,4]

Output: [1,3,4]
```
#### Explanation:
![image](https://github.com/user-attachments/assets/fb767f08-fe8e-4175-b651-323583e38f7a)

#### Example 2:
```plaintext
Input: root = [1,2,3,4,null,null,null,5]

Output: [1,3,4,5]
```
#### Explanation:
![image](https://github.com/user-attachments/assets/610f9584-b1f4-4dfa-98c4-a474ca6e27a1)

#### Example 3:
```plaintext
Input: root = [1,null,3]

Output: [1,3]
```
#### Example 4:
```plaintext
Input: root = []

Output: []
```
 

#### Constraints:
```plaintext
The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
```

---

## Solution
By looking at the solution, we know that we either have to traverse using DFS or BFS. And also the last element of each level should be used for the output.

So As we traverse through the elements in each level using BFS we can only extract last element at each level.

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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> output = new ArrayList<>();
        List<TreeNode> currentLevel = new ArrayList<>();
        if(root!=null){
            currentLevel.add(root);
        }

        while(!currentLevel.isEmpty()){
            output.add(currentLevel.get(currentLevel.size()-1).val);
            List<TreeNode> nextLevel = new ArrayList<>();
            for(TreeNode t : currentLevel){
                if(t.left!=null) nextLevel.add(t.left);
                if(t.right!=null) nextLevel.add(t.right);
            }
            currentLevel=nextLevel;
        }
        return output;
    }
}
```

Here we have an output. We have the current Level and next level. First, we start with root which is the current lvel and so we add it. While the current level elements are not empty, we did not reach the last level.

We add the last element of the current level into the output. Then we add left and right of the current level elements to the next level. and then make the next level as current level. Pick the last element and store in the output.

Time Complexity and Space complexity is O(N)

We can also use DFS for thsi problem, where we go to each level and pass the level along with the elements. As we traverse, if we check the array already have and element corresponding to the level then dont add else add it. But we should traverse from right
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
    public void preorder(TreeNode node, int level,List<Integer> output){
        if(node == null) return;
        if(level==output.size()){
            output.add(node.val);
        }
        preorder(node.right,level+1,output);
        preorder(node.left,level+1,output);
    }
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> output = new ArrayList<>();
        preorder(root,0,output);
        return output;
    }
}
```
The Time Complexity is O(N) and Space complexity is O(H) where H is the number of levels
