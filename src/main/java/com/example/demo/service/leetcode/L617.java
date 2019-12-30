package com.example.demo.service.leetcode;

import com.example.demo.service.leetcode.common.TreeNode;

/**
 * @Author: laojiaqi
 * @Date: 2019-11-24 20:49
 * @Description:
 */
public class L617 {

    public static void main(String[] args) {
        TreeNode t1=new TreeNode(1);
        t1.setRight(new TreeNode(2));
        TreeNode treeNode = new TreeNode(3);
        treeNode.setLeft(new TreeNode(5));
        t1.setLeft(treeNode);


        TreeNode t2=new TreeNode(2);
        t2.setRight(new TreeNode(3));
        TreeNode treeNode1 = new TreeNode(1);
        treeNode1.setRight(new TreeNode(4));
        t2.setLeft(treeNode1);

        mergeTrees(t1,t2);

        System.out.println(t1);
    }


    public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1==null && t2==null){
            return null;
        }
        if(t1!=null && t2==null){
            return t1;
        }
        if(t1==null && t2!=null){
            t1=t2;
            return t1;
        }
        t1.val=t1.val+t2.val;
        TreeNode left = mergeTrees(t1.left, t2.left);
        TreeNode right = mergeTrees(t1.right, t2.right);
        t1.setLeft(left);
        t1.setRight(right);
        return t1;
    }

}
