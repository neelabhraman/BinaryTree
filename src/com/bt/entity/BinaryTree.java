package com.bt.entity;
import lombok.Data;

@Data
public class BinaryTree {
    BinaryTree left;
    BinaryTree right;
    Object value;
}