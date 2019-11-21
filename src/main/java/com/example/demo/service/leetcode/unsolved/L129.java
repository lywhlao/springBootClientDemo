package com.example.demo.service.leetcode.unsolved;

import com.example.demo.service.leetcode.common.TreeNode;

import java.util.List;

/**
 * @Author: laojiaqi
 * @Date: 2019-11-20 22:55
 * @Description:
 */
public class L129 {

    public static void main(String[] args) {
        sumNumbers(new TreeNode(1));
    }

    public static int sumNumbers(TreeNode root) {
        TreeNode treeNode=new TreeNode(1);
        treeNode.setLeft(new TreeNode(2));
        treeNode.setRight(new TreeNode(3));
        int value = getValue(treeNode, 0);
        System.out.println(value);
        return 0;
    }

    public static int getValue(TreeNode root, int value){
        if(root==null){
            return 0;
        }
        int i = value * 10 + root.val;
        int value1=0;
        int value2=0;
        if(root.left!=null){
             value1 = getValue(root.left, i);
        }
        if(root.right!=null){
            value2 = getValue(root.right, i);
        }
        return i+value1+value2;
    }


}
