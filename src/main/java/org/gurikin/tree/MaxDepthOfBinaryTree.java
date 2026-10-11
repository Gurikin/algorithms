package org.gurikin.tree;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */

class MaxDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        var result = 0;
        var leftDepth = 0;
        var rightDepth = 0;
        if (root == null) {
            return 0;
        }
        result++;
        if (root.left != null) {
            leftDepth += maxDepth(root.left);
        }
        if (root.right != null) {
            rightDepth += maxDepth(root.right);
        }
        return (result += Integer.max(leftDepth, rightDepth));
    }

    public static void main(String[] args) {

    }
}