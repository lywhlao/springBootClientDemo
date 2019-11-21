package com.example.demo.service.leetcode;

import com.example.demo.service.leetcode.common.TreeNode;

/**
 * @Author: laojiaqi
 * @Date: 2019-11-19 21:39
 * @Description:
 */
public class L965 {

    public static void main(String[] args) {

    }
    public static boolean isUnivalTree(TreeNode root) {
        if(root==null){
            return true;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        if(left!=null){
            if(root.getVal() != left.getVal()){
                return false;
            }
        }
        if(right!=null){
            if(root.getVal() != right.getVal()){
                return false;
            }
        }
       return isUnivalTree(root.left)&&isUnivalTree(root.right);
    }

}
