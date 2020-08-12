package com.wcq.tree;

import com.wcq.other.TreeLike;

import java.util.function.BiConsumer;

public class SortTree<K, V> implements TreeLike<K, V> {
    private Node root;

    private class Node implements Comparable<Node>{
        private K key;
        private V value;
        private Node left;
        private Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            Comparable key1 = (Comparable<? extends  K>)key;
            Comparable key2 = (Comparable<? extends  K>)o;
            return key1.compareTo(key2);
        }
    }

    @Override
    public V insert(K key, V value) {
        Node newNode = new Node(key, value);

        if(root == null){
            root = newNode;
            return value;
        }
        return insert(root, newNode);
    }

    private V insert(Node node, Node newNode) {
        int compare = newNode.compareTo(node);
        // 相等
        if(compare == 0){
            // 接管
            return takeOver(newNode, node);
        }else if(compare < 0){
            if(node.left != null){
                return insert(node.left, newNode);
            }else{
                node.left = newNode;
                return newNode.value;
            }
        }else{
            if(node.right != null){
                return insert(node.right, newNode);
            }else{
                node.right = newNode;
                return newNode.value;
            }
        }
    }

    private V takeOver(Node newNode, Node oldNode) {
        newNode.left = oldNode.left;
        newNode.right = oldNode.right;
        return oldNode.value;
    }

    @Override
    public V search(K key) {
        Node newNode = new Node(key, null);
        return search(root, newNode);
    }

    private V search(Node node, Node newNode) {
        if(node == null){
            return null;
        }

        int compare = newNode.compareTo(node);
        if(compare == 0){
            return node.value;
        }else if(compare < 0){
            return search(node.left, newNode);
        }else{
            return search(node.right, newNode);
        }
    }

    @Override
    public void preOrderTraverse(BiConsumer consumer) {
        preOrder(root, consumer);
    }

    @Override
    public void postOrderTraverse(BiConsumer consumer) {
        postOrder(root, consumer);
    }

    public void preOrder(Node root, BiConsumer consumer){
        if(root != null){
            consumer.accept(root.key, root.value);
            preOrder(root.left, consumer);
            preOrder(root.right, consumer);
        }
    }

    public <T, U> void postOrder(Node root, BiConsumer<K, V> consumer){
        if(root != null){
            postOrder(root.left, consumer);
            postOrder(root.right, consumer);
            consumer.accept(root.key, root.value);
        }
    }
}
