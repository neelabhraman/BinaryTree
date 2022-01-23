package com.binTreeLeetCode;


import sun.reflect.generics.tree.Tree;

/**
 * Definition for a binary tree node.*/
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Main {
    
    public static void main(String [] args){

        TreeNode node6= new TreeNode(6);


        TreeNode node5= new TreeNode(5);

        TreeNode node8= new TreeNode(8);

        TreeNode node7= new TreeNode(7,node8,null);


        TreeNode node4= new TreeNode(4);

        TreeNode node3= new TreeNode(3,node6,node7);


        TreeNode node2= new TreeNode(2,node4,node5);



        TreeNode treeNodeRoot= new TreeNode(1,node2,node3);
        Main main=new Main();

        main.inorderMorrisAlgo(treeNodeRoot);
       System.out.println("Max Depth: "+ main.maxDepth(treeNodeRoot));
    }
/*this is the Morris Inorder traversal
*
* https://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion-and-without-stack/*/
    public void inorderMorrisAlgo(TreeNode root){

        TreeNode current=root;
        TreeNode pre;
        while(current !=null){
            if(current.left==null){
                System.out.println(current.val);
                current=current.right;
            }
            else{
                pre=current.left;
                while(pre.right !=null && pre.right!=current){
                pre=pre.right;
                }

                if(pre.right==null){
                    pre.right=current;
                    current=current.left;
                }
                else{
                    pre.right=null;
                    System.out.println(current.val);
                    current=current.right;
                }

            }

        }
    }

    /*Given the root of a binary tree, return its maximum depth.

A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.*/

    public int maxDepth(TreeNode root){
            if(null ==root){
                return 0;
            }
            return (1+ Math.max(maxDepth(root.left),maxDepth(root.right)));

    }

}
