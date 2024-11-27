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
    private int diameter;
    int DFS(TreeNode root){
        if(root==null) return 0;
       int leftPath = DFS(root.left); //height of left subtree
       int rightPath = DFS(root.right); //height of right subtree

        diameter = Math.max(diameter,leftPath+rightPath); //update diameter
        return Math.max(leftPath,rightPath)+1; // return currrent subtree height
    }
    public int diameterOfBinaryTree(TreeNode root) {
        diameter = 0;
        DFS(root);
        return diameter;
    }
}
