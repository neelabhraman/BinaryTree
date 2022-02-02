package com.binTreeLeetCode;


import com.bbt.entity.BinaryTree;
import sun.reflect.generics.tree.Tree;

import java.util.*;

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

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        List<Integer> leafNodeArr=new ArrayList<>();
        int maxTime=0;
        int timeForLeafToRoot=0;
        Deque<Integer> queueTraversal= new ArrayDeque<>();
        Map<Integer,Integer> mapForOptimization= new HashMap();


        if(n==1){
            return 0;
        }
        else{
            for(int j=0;j<n;j++){
                if(Arrays.asList(manager).indexOf(j)==-1){
                    leafNodeArr.add(j);
                }
            }

            for(int i=0;i<leafNodeArr.size();i++){
                timeForLeafToRoot=0;
                int leafNode=leafNodeArr.get(i);
                queueTraversal.clear();
                queueTraversal.push(manager[leafNode]);
                while(!queueTraversal.isEmpty()){
                    int nodeVal=queueTraversal.pop();
                    if(mapForOptimization.containsKey(nodeVal)){
                        timeForLeafToRoot=timeForLeafToRoot+mapForOptimization.get(nodeVal);
                        break;
                    }
                    else if(nodeVal>-1){
                        timeForLeafToRoot=timeForLeafToRoot+informTime[nodeVal];
                        queueTraversal.push(manager[nodeVal]);
                    }
                }
                //push to optimum map
                if(!mapForOptimization.containsKey(manager[leafNode])){
                    mapForOptimization.put(manager[leafNode],timeForLeafToRoot);
                }
                //push to optimum map end
                if(timeForLeafToRoot>maxTime){
                    maxTime=timeForLeafToRoot;
                }
            }

        }
        return maxTime;
    }
    //merge sort code

    /*

    var sortArray = function(nums) {

    return breakArr(nums);


};

var breakArr=function(nums){


    if(nums.length ===1){
        return nums;
    }
    else{
        let divisionPoint=Math.ceil(nums.length/2);
        let leftArr=nums.slice(0,divisionPoint);
        let rightArr=nums.slice(divisionPoint,nums.length);
        let leftArrFin=breakArr(leftArr);
        let rightArrFin=breakArr(rightArr);
       let finArr = merge(leftArrFin,rightArrFin,nums.length);

        return finArr;

    }

}



var merge = function(nums1,nums2,size) {
 let m=nums1.length;
 let n=nums2.length;
    let finArr=[];
    let i=0;
    let j=0;

    if(nums1.length===0 && nums2.length===0){
        return  [];
    }

    while(finArr.length <= size){
        if(i=== m){
            finArr=finArr.concat(nums2.slice(j,n));
            break;
        }
        else if(j=== n){
            finArr=finArr.concat(nums1.slice(i,m));
            break;
        }
     if(nums1[i] <nums2[j]){
         finArr.push(nums1[i] );
         i++;
     }else{
         finArr.push(nums2[j]);
         j++;
     }

    }

     return finArr;

};
    * */

}
