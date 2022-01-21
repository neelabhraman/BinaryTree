package com.bbt;


import com.bbt.entity.BinaryTree;
import com.bbt.svc.SuperBalancedSvc;

public class Main {

    public static void main(String[] args) {


        BinaryTree node5= new BinaryTree();
        node5.setValue(new Integer(5));

        BinaryTree node6= new BinaryTree();
        node6.setValue(new Integer(6));



        BinaryTree node8= new BinaryTree();
        node8.setValue(new Integer(8));


        BinaryTree node7= new BinaryTree();
        node7.setValue(new Integer(7));
        //node7.setLeft(node8);
        node7.setRight(node8);

        BinaryTree node4= new BinaryTree();
        node4.setValue(new Integer(4));
        node4.setLeft(node6);


        BinaryTree node3= new BinaryTree();
        node3.setValue(new Integer(3));
        //node3.setLeft(node6);
       // node3.setRight(node7);

        BinaryTree node2= new BinaryTree();
        node2.setValue(new Integer(2));
        node2.setLeft(node4);
        node2.setRight(node5);


        BinaryTree binaryTreeRoot= new BinaryTree();
        binaryTreeRoot.setValue(new Integer(1));
        binaryTreeRoot.setLeft(node2);
       binaryTreeRoot.setRight(node3);


        //print it
        SuperBalancedSvc superBalancedSvc=new SuperBalancedSvc();
        /*This is my implementation*/
        System.out.println(superBalancedSvc.isBalanced(binaryTreeRoot));
        superBalancedSvc.printTreeDepthWise(binaryTreeRoot);
        /*this is the implementation from the InterviewCake*/
        System.out.println(superBalancedSvc.isBalancedDepthTraversal(binaryTreeRoot));
    }
}
