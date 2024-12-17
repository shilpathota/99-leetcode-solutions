# All Nodes Distance K in Binary Tree

## Leet code Link - https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/description/

## Complexity - Medium

## Description
Given the root of a binary tree, the value of a target node target, and an integer k, return an array of the values of all nodes that have a distance k from the target node.

You can return the answer in any order.

 #### Example 1:
 <img src = "https://s3-lc-upload.s3.amazonaws.com/uploads/2018/06/28/sketch0.png" />
```
 Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
Output: [7,4,1]
Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.
```
#### Example 2:
```
Input: root = [1], target = 1, k = 3
Output: []
 ```

#### Constraints:
```
The number of nodes in the tree is in the range [1, 500].
0 <= Node.val <= 500
All the values Node.val are unique.
target is the value of one of the nodes in the tree.
0 <= k <= 1000
```
---

## Solution
As we have to get the levels we can use BFS or DFS. 
1. We should find the target
2. Find the level from the target in child
3. For the parent, we should find the nodes at that distance

This can be achieved in 2 ways.
1. Using Parent pointer which keeps track of the parent of each node that we iterate.
2. Using recursion find the levels and the distance of left and right nodes

Here is the second approach for recursion

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
    List<Integer> output = new ArrayList<Integer>();

    public void findLevels(TreeNode root,int level){
        if(root == null) return;
        if(level==0){
            output.add(root.val);
            return;
        }
        findLevels(root.left,level-1);
        findLevels(root.right,level-1);
    }
    public int distanceRec(TreeNode root, TreeNode target,int level){
        if(root == null) return -1;
        if(root.val == target.val){
            findLevels(root,level);
            return 1;
        }
        int left = distanceRec(root.left,target,level);
        if(left != -1){
            if(level==left){
                output.add(root.val);
            }
            else{
                findLevels(root.right,level-left-1);
            }
            return left+1;
        }
        int right = distanceRec(root.right,target,level);
        if(right!=-1){
            if(level==right){
                output.add(root.val);
            }
            else{
                findLevels(root.left,level-right-1);
            }
           
            return right+1;
        }
        return -1;
    }
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
       distanceRec(root,target,k);
       return output;
    }
}
```
Space Complexity and Time Complexity is O(N)

Using Parent pointers
```java
class Solution {
 List<Integer> result = new ArrayList<>();
 Map<TreeNode,TreeNode> parentmap = new HashMap<>();
 Set<TreeNode> visited = new HashSet<>();

 public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
     buildmap(root);
     FindTarget(target,0,k);
     return result;
 }

 public void buildmap(TreeNode node){
     if(node.left != null){
         parentmap.put(node.left,node);
         buildmap(node.left);
     }
     if(node.right != null){
         parentmap.put(node.right,node);
         buildmap(node.right);
     }
 }

 public void FindTarget(TreeNode node ,int distance , int k){

    if(node == null || visited.contains(node)){
     return;
    }
    visited.add(node);
    
     if(distance == k){
         result.add(node.val);
         return ;
     }
     if(node.left != null){
         FindTarget(node.left,distance+1,k);
     }
     if(node.right != null){
         FindTarget(node.right,distance+1,k);
     }
     if(parentmap.get(node) != null){
         FindTarget(parentmap.get(node),distance+1,k);
     }
 }
}
```

Found other simple solution without parent pointer but used the hashmap

```java
class Solution {
    
    Map<TreeNode, Integer> map = new HashMap<>();
        
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new LinkedList<>();
        find(root, target);
        dfs(root, target, K, map.get(root), res);
        return res;
    }
    
    // find target node first and store the distance in that path that we could use it later directly
    private int find(TreeNode root, TreeNode target) {
        if (root == null) return -1;
        if (root == target) {
            map.put(root, 0);
            return 0;
        }
        int left = find(root.left, target);
        if (left >= 0) {
            map.put(root, left + 1);
            return left + 1;
        }
		int right = find(root.right, target);
		if (right >= 0) {
            map.put(root, right + 1);
            return right + 1;
        }
        return -1;
    }
    
    private void dfs(TreeNode root, TreeNode target, int K, int length, List<Integer> res) {
        if (root == null) return;
        if (map.containsKey(root)) length = map.get(root);
        if (length == K) res.add(root.val);
        dfs(root.left, target, K, length + 1, res);
        dfs(root.right, target, K, length + 1, res);
    }
}
```
Without using hashmap

``java
class Solution {
            
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new LinkedList<>();
        if (K == 0) {
            res.add(target.val);
        } else {
            dfs(res, root, target.val, K ,0);
        }
        return res;
    }
    
    private int dfs(List<Integer> res, TreeNode node, int target, int K, int depth) {
        if (node == null) return 0;
        
        if (depth == K) {
            res.add(node.val);
            return 0;
        }
        
        int left, right;
        if (node.val == target || depth > 0) {
            left = dfs(res, node.left, target, K, depth + 1);
            right = dfs(res, node.right, target, K, depth + 1);
        } else {
            left = dfs(res, node.left, target, K, depth);
            right = dfs(res, node.right, target, K, depth);
        }
        
        if (node.val == target) return 1;
        
        if (left == K || right == K) {
            res.add(node.val);
            return 0;
        }
        
        if (left > 0) {
            dfs(res, node.right, target, K, left + 1);
            return left + 1;
        }
        
        if (right > 0) {
            dfs(res, node.left, target, K, right + 1);
            return right + 1;
        }
        
        return 0;
    }
}
```
