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


    //QUICK SORT IMPL

    /*
    var sortArray = function(nums) {


    quickSortImpl(nums,nums.length,0,nums.length-1);

    return nums;

};


    const quickSortImpl=function(arr,arrSize,startIndex,endIndex){
        //startIndex will be 0 in the beginning
    //quick sort implementation
    //pivot will be the last element

        let pivot = endIndex;
        let temp;
        if(arrSize<=1){
           return;
           }
        for(let i=startIndex;i<=pivot;i++){
            if(arr[i]>arr[pivot]){
                //do 3 things when pivot item is smaller
                temp=arr[pivot];
                arr[pivot]=arr[i];
                arr[i]=arr[pivot-1];
                arr[pivot-1]=temp;
                pivot--;
                //start again
                i=-1;
            }else if(i===pivot){
                     let arrSizeLeft;
                     let startIndexLeft;
                     let endIndexLeft;

                     let arrSizeRight;
                     let startIndexRight;
                     let endIndexRight;

                     //size calculation
                     arrSizeLeft=pivot-startIndex;
                     arrSizeRight=endIndex-pivot;

                     //start index calculation
                        startIndexLeft=startIndex;
                        startIndexRight=pivot+1;

                    //set end index
                    endIndexLeft=pivot-1;
                    endIndexRight=endIndex;
                //call left
                quickSortImpl(arr,arrSizeLeft,startIndexLeft,endIndexLeft);
                //call right
                quickSortImpl(arr,arrSizeRight,startIndexRight,endIndexRight);
                     }
        }

    };

    * */
    public int[] sortArray(int[] nums) {

        quickSortImpl(nums,nums.length,0,nums.length-1);

        return nums;
    }


    public void quickSortImpl(int[] arr,int arrSize,int startIndex,int endIndex){
        //startIndex will be 0 in the beginning
        //quick sort implementation
        //pivot will be the last element

        int pivot = endIndex;
        int temp;
        if(arrSize<=1){
            return;
        }
        for(int i=startIndex;i<=pivot;i++){
            if(arr[i]>arr[pivot]){
                //do 3 things when pivot item is smaller
                //swap
                //change pivot
                //restart loop

                temp=arr[pivot];
                arr[pivot]=arr[i];
                arr[i]=arr[pivot-1];
                arr[pivot-1]=temp;
                //
                pivot--;
                //start again
                i=-1;
            }else if(i==pivot){
                int arrSizeLeft;
                int startIndexLeft;
                int endIndexLeft;

                int arrSizeRight;
                int startIndexRight;
                int endIndexRight;

                //size calculation
                arrSizeLeft=pivot-startIndex;
                arrSizeRight=endIndex-pivot;

                //start index calculation
                startIndexLeft=startIndex;
                startIndexRight=pivot+1;

                //set end index
                endIndexLeft=pivot-1;
                endIndexRight=endIndex;
                //call left
                if(arrSizeLeft>0){
                    quickSortImpl(arr,arrSizeLeft,startIndexLeft,endIndexLeft);
                }
                //call right
                if(arrSizeRight>0){
                    quickSortImpl(arr,arrSizeRight,startIndexRight,endIndexRight);
                }
            }
        }

    }

    /*Count sort*/

    /*
    1, 4, 1, 2, 7, 5, 2


    let countArr=[0,0,0,0,0,0,0,0,0,0];-- for base 10, i.e. 0 to 9
    let outputArr=[];
    for (let i=0;i<arrInp.length;i++){
    countArr[arrInp[i]]++;

    }

    for (let i=1;i<countArr.length;i++){
    countArr[i]+=countArr[i-1];
    }

    console.log("countArr before",countArr);
    for(let i=arrInp.length-1;i>=0;i--){
    let valueToPut=arrInp[i];
    countArr[valueToPut]--;
    outputArr[countArr[valueToPut]]=valueToPut;
    }

    console.log("countArr after",countArr);
    console.log("arrInp",arrInp);
    console.log("outputArr",outputArr);
    * */



    //Radix sort

    /*


    var sortArray = function(nums) {

    if(nums.length <=1){
    return nums;
    }
    //radix sort

    return radixImpl(nums);

};
//to do normalization


var radixImpl=function(arr){


        let maxNumber=arr[0];
        let smallestNumber=arr[0];
        let maxDigit=0;
        let isNormalized=false;
        for(let i=0;i<arr.length;i++){
        if(arr[i]>maxNumber){
            maxNumber=arr[i];
            }
         if(arr[i]<smallestNumber){
            smallestNumber=arr[i];
            }
        }
        //check if we have -ive #s
        //if yes normalize the array
        if(smallestNumber<0){
        isNormalized=true;
        for(let i=0;i<arr.length;i++){
            arr[i]=arr[i]+Math.abs(smallestNumber);
        }
           maxNumber=maxNumber +Math.abs(smallestNumber);
        }



        //now we know the maximim # of digits.
        maxDigit=maxNumber.toString().length;



        //# of passes will be maximum digits
        for(let i=0;i<maxDigit;i++){
            arr=countSortSubRoutine(arr,i);
        }

        if(isNormalized){
            for(let i=0;i<arr.length;i++){
            arr[i]=arr[i]-Math.abs(smallestNumber);
             }
        }
        return arr;
    };
    var countSortSubRoutine=function(arr,pos){
        //represents digits 0 to 9
    let countArr=[0,0,0,0,0,0,0,0,0,0];
        let finArr=[];
    // populate count
            for(let i=0;i<arr.length;i++){
            countArr[(Math.floor(arr[i]/Math.pow(10,pos)))%10]++;
            }


        //cumulate;

        for(let i=1;i<countArr.length;i++){
            countArr[i]+=countArr[i-1];
        }


        //start populating based on the indexes identified
        for(let i=arr.length-1;i>=0;i--){
            let valueToPut=arr[i];
            countArr[(Math.floor(arr[i]/Math.pow(10,pos)))%10]--;
            finArr[countArr[(Math.floor(arr[i]/Math.pow(10,pos)))%10]]=valueToPut;
        }
        return finArr;

    };


    * */
}
