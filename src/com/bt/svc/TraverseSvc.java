package com.bt.svc;


import com.bt.entity.BinaryTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;


public class TraverseSvc {

    /*The method expects a node and prints the value
    * then it returns the left and right nodes*/
    /*This traversal (unknowingly) implements  ROOT-LEFT-RIGHT ==> Preorder of Depth First Traversals
     * In my opinion there's a better way hence deprecating it*/
    @Deprecated
    public List<BinaryTree> printNodeValue(BinaryTree node){
        List<BinaryTree> childNodes = new ArrayList<>();
        if(null!=node){
            System.out.println(node.getValue());
            BinaryTree leftChild=node.getLeft();
            BinaryTree rightChild=node.getRight();
            if(null!=leftChild){
                childNodes.add(leftChild);
            }
            if(null!=rightChild){
                childNodes.add(rightChild);
            }
        }
        return childNodes;
    }
    @Deprecated
    /*this method will print whole node recursively*/
    public  void printTree(BinaryTree root) {
        List<BinaryTree> childNode = printNodeValue(root);
        if (null != childNode ) {
            if (childNode.size() == 2) {
            printTree(childNode.get(0));
            printTree(childNode.get(1));
            } else if (childNode.size() == 1) {
                printTree(childNode.get(0));
            }
        }

    }



    /*New approach starts here
    * this approach will create List for each level
    * so that later we can use it to print/ mine other details of the Binary tree*/


    public void convertToLinearLevelWrapper(BinaryTree node,List <Object> currentLevelValue){
        Deque<String> dequeForLevelN = new ArrayDeque<>();
        List <Object> nextLevelNodes=new ArrayList<>();
        currentLevelValue.add(node.getValue());
        BinaryTree leftChild=node.getLeft();
        BinaryTree rightChild=node.getRight();
        nextLevelNodes.add(leftChild);
        nextLevelNodes.add(rightChild);


    }
    public void convertToLinearLevel(BinaryTree node,List <Object> currentLevelValue){

    }
}