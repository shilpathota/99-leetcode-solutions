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
