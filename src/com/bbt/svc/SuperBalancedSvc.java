package com.bbt.svc;


import com.bbt.entity.BinaryTree;

import java.util.*;


public class SuperBalancedSvc {
private static final String LEAF_NODE="LN";
    /*A tree is "superbalanced" if the difference between the depths of any two
       leaf nodes is no greater than one.*/
    public boolean isBalanced( BinaryTree root) {
    boolean returnVal=true;
        List<List<Object>> linearCompoundStruct= new ArrayList<>();
        List<Object> linearStruct= new ArrayList<>();
        if(null == root){
            return true;
        }
        Deque<BinaryTree> dQueue= new ArrayDeque<>();
        dQueue.add(root);
        do{
            readNode(dQueue,linearStruct);
        }while (!dQueue.isEmpty());

        linearCompoundStruct= linearCompoundStructConverter(linearStruct);
        System.out.println("After creation of compound Struct"+linearCompoundStruct);
        linearCompoundStruct=removeNullValues(linearCompoundStruct);
        System.out.println("Final Output: "+linearCompoundStruct);

        /*Method to use the compound structure to identify height diff*/
        int maxHeightDiff=determineMaxHeightDiff(linearCompoundStruct);
        System.out.println("maxHeightDiff: "+maxHeightDiff);
        if(maxHeightDiff>1){
            returnVal=false;
        }
        return returnVal;
    }
    public void readNode(Deque<BinaryTree> queue, List<Object> linearStruct) {
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
        //Identified the leaf node
        if(node.getLeft() ==null && node.getRight() ==null && null!=node.getValue()){
            createLinearStruct(LEAF_NODE,linearStruct);
        }else{
            createLinearStruct(node.getValue(),linearStruct);
        }

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

    public int  determineMaxHeightDiff( List<List<Object>> linearCompoundStruct) {
        int maxHeightDiff = 0;
        int upperMostNode=-1;
        int deepestNode=-1;
        for(int level=0;level<linearCompoundStruct.size();level++){
            if(linearCompoundStruct.get(level).indexOf(LEAF_NODE) !=-1){
                upperMostNode=level;
                break;
            }
        }
        for(int level=linearCompoundStruct.size()-1;level>-1;level--){
            if(linearCompoundStruct.get(level).indexOf(LEAF_NODE) !=-1){
                deepestNode=level;
                break;
            }
        }
        maxHeightDiff=deepestNode-upperMostNode;
        return maxHeightDiff;
    }
    }
