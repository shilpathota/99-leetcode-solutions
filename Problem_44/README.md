# Lowest Common Ancestor of a Binary Tree III

## Leet Code Link - https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/description/

## Complexity - Medium

## Description
Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).

Each node will have a reference to its parent node. The definition for Node is below:

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}
According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)."

 

#### Example 1:

![image](https://github.com/user-attachments/assets/205bac90-4bd9-4ce6-962e-7eb6f6593d99)
```plaintext
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
```
#### Example 2:
![image](https://github.com/user-attachments/assets/306ca264-e9a4-471f-b438-b3a6df9c221d)
```plaintext
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5 since a node can be a descendant of itself according to the LCA definition.
```
#### Example 3:
```plaintext
Input: root = [1,2], p = 1, q = 2
Output: 1
 ```

#### Constraints:
```plaintext
The number of nodes in the tree is in the range [2, 105].
-109 <= Node.val <= 109
All Node.val are unique.
p != q
p and q exist in the tree.
```

---

## Solution
When I looked at the problem first thing that comes to my mind is iterate over the parents until it is null and then compare it with the other parent

So for iterating I used while loop and comparing I used a list of nodes where I stored the ones I visited. if it is present in that list then I return the element as it matches with the parent.

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
*/

class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        List<Node> seen = new ArrayList<>();
        seen.add(p);seen.add(q);
        while(p.parent!=null){
            if(!seen.contains(p.parent)){
            seen.add(p.parent);
            System.out.println(p.parent.val);
            p=p.parent;
            }else{
                return p.parent;
            }
        }
        while(q.parent!=null){
            if(!seen.contains(q.parent)){
                seen.add(q.parent);
                System.out.println(q.parent.val);
                q=q.parent;
            }
            else{
                return q.parent;
            }
        }
        return q!=null?q:p;
    }
}
```
#### Complexity
The Time complexity is O(N) which is linear

The space complexity is also linear O(N) as I'm storing the elements.

Let us see if there is any other optimized solution without storing the elements is one optimization I can think of.

```java
class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        Node a = p, b = q;
        while (a != b) {
            a = a == null ? q : a.parent;
            b = b == null ? p : b.parent;
        }
        return a;
    }
}
```
This is the solution I found where they are not using the element storage and simple conditions 


