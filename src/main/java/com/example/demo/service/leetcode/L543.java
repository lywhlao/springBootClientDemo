package com.example.demo.service.leetcode;

import com.example.demo.service.leetcode.common.TreeNode;

/**
 * @Author: laojiaqi
 * @Date: 2019-11-18 11:34
 * @Description:
 */
public class L543 {


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
//        TreeNode treeNode = new TreeNode(2);
//        root.setRight(treeNode);
//        root.setLeft(new TreeNode(3));
//
//        TreeNode treeNode1 = new TreeNode(4);
//        treeNode.setLeft(treeNode1);
//        treeNode.setRight(new TreeNode(5));
//
//        treeNode1.setLeft(new TreeNode(6));
        int i = diameterOfBinaryTree(root);

        System.out.println(i);
    }

    public static int max = 0;

    public static int diameterOfBinaryTree(TreeNode root) {
        calculateLength(root);
        return max-1;
    }

    public static int calculateLength(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = calculateLength(root.left);
        int right = calculateLength(root.right);
        int i = 1 + left + right;
        //record max
        if (i > max) {
            max = i;
        }
        return 1 + Math.max(left, right);
    }

}
