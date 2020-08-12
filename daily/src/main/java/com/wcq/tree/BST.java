package com.wcq.tree;

import org.apache.poi.ss.usermodel.RichTextString;

public class BST {
    private Node root;

    private static class Node{
        private Object value;
        private Node left;
        private Node right;
    }

    public static void preOrder(Node root){
        if(root != null){
            System.out.println(root.value + "->");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    public static void midOrder(Node root){
        if(root != null){
            midOrder(root.left);
            System.out.println(root.value + "->");
            midOrder(root.right);
        }
    }

    public static void postOrder(Node root){
        if(root != null){
            postOrder(root.left);
            postOrder(root.right);
            System.out.println(root.value + "->");
        }
    }


}
