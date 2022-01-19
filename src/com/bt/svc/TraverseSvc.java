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
           // System.out.println(node.getValue());
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
            List<List<Object>> linearCompoundStruct= new ArrayList<>();
            if(null == root){
                System.out.println("Final Output: "+linearCompoundStruct);
                return ;
            }
            List<Object> linearStruct= new ArrayList<>();
            Deque<BinaryTree> queue= new ArrayDeque<>();
            queue.add(root);
            do{
                queue=readNode(queue,linearStruct);
            }while (!queue.isEmpty());

            System.out.println("After do while: "+linearStruct);
            linearCompoundStruct= linearCompoundStructConverter(linearStruct);
            System.out.println("After creation of compound Struct"+linearCompoundStruct);
            linearCompoundStruct=removeNullValues(linearCompoundStruct);
            System.out.println("Final Output: "+linearCompoundStruct);

        }
        public Deque<BinaryTree> readNode(Deque<BinaryTree> queue,List<Object> linearStruct) {
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
            //push to the single dimension array for later manipulation
            createLinearStruct(node.getValue(),linearStruct);
            return queue;
        }


        public void createLinearStruct(Object value,List<Object> linearStruct) {
            linearStruct.add(value);
        }

        public List<List<Object>> linearCompoundStructConverter(List<Object> linearStruct) {
            int lastElementIndex=-1;
            int origSize=linearStruct.size();
            for(int i=linearStruct.size()-1;i>-1;i--){
                if(null!= linearStruct.get(i)){
                    lastElementIndex=i;
                    break;
                }
            }
            //remove trailing null
            linearStruct.subList(lastElementIndex+1,origSize).clear();
            int numberofLevel=0;
            int linearArrSize= linearStruct.size();
            System.out.println("Cleaned list "+ linearStruct+" Size ==> "+ linearArrSize);
            List<List<Object>> linearCompoundStruct= new ArrayList<>();

            int level=0;
            int rowSizeForLevel=0;
            int arrayPointer=0;
            while(arrayPointer<linearArrSize){
                if(level ==0){
                    rowSizeForLevel=1;
                }
                else{
                    List <Object>previousLevel= linearCompoundStruct.get(level-1);
                    previousLevel.removeAll(Collections.singleton(null));
                    rowSizeForLevel=previousLevel.size()*2;
                }
                List <Object>temp=new ArrayList<>();
                int itemLengthSublist=arrayPointer+rowSizeForLevel;
                if(itemLengthSublist>linearArrSize){
                    itemLengthSublist=linearArrSize;
                }
                temp=new ArrayList<>(linearStruct.subList(arrayPointer,itemLengthSublist));
                linearCompoundStruct.add(level, temp);
                arrayPointer=arrayPointer+rowSizeForLevel;
                level++;
            }
            return linearCompoundStruct;
        }

        public List<List<Object>> removeNullValues(List<List<Object>> linearCompoundStruct) {
            for(int i=1;i<linearCompoundStruct.size();i++){
                linearCompoundStruct.get(i).removeAll(Collections.singleton(null));
            }
            return linearCompoundStruct;
        }
    }