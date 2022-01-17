package com.bt.svc;


import com.bt.entity.BinaryTree;

import java.util.*;


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


    /*Using Deque as queue here
    * add will add at tail of the queue
    * remove will remove from head of the queue*/
    public void readTree(BinaryTree root){
        Deque<BinaryTree> queue= new ArrayDeque<>();
        queue.add(root);
        do{
            queue=readNode(queue);
        }while (!queue.isEmpty());
    }
    public Deque<BinaryTree> readNode(Deque<BinaryTree> queue) {
        BinaryTree node = queue.remove();
        if(node.getLeft()!=null){
        queue.add(node.getLeft());
        }
        /*this condition is added so that we can have null children for better manifestation of tha tree later*/
        else if(null!=node.getValue()){
            queue.add(new BinaryTree());
        }
        if(node.getRight()!=null) {
            queue.add(node.getRight());
        }
        /*this condition is added so that we can have null children for better manifestation of tha tree later*/
        else if(null!=node.getValue()){
            queue.add(new BinaryTree());
        }
        System.out.println(node.getValue());
        return queue;
    }
    }