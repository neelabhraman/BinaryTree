package com.bt;

import com.bt.entity.BinaryTree;
import com.bt.svc.TraverseSvc;

public class Main {

    public static void main(String[] args) {

        BinaryTree binaryTreeLevel2Left= new BinaryTree();
        binaryTreeLevel2Left.setValue(new Integer(4));


        BinaryTree binaryTreeLevel2Right= new BinaryTree();
        binaryTreeLevel2Right.setValue(new Integer(5));

        BinaryTree binaryTreeLevel1Left= new BinaryTree();
        binaryTreeLevel1Left.setValue(new Integer(2));
        binaryTreeLevel1Left.setLeft(binaryTreeLevel2Left);
        binaryTreeLevel1Left.setRight(binaryTreeLevel2Right);

        BinaryTree binaryTreeLevel1Right= new BinaryTree();
        binaryTreeLevel1Right.setValue(new Integer(3));


        // creating new Binary tree
        BinaryTree binaryTreeRoot= new BinaryTree();
        binaryTreeRoot.setValue(new Integer(1));
        binaryTreeRoot.setLeft(binaryTreeLevel1Left);
        binaryTreeRoot.setRight(binaryTreeLevel1Right);


        //print it
        TraverseSvc traverseSvc=new TraverseSvc();
        traverseSvc.printTree(binaryTreeRoot);
    }
}
