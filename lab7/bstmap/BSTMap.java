package bstmap;

import edu.princeton.cs.algs4.Stack;

import java.util.*;

public class BSTMap<K extends Comparable<K>, V>  implements Map61B<K, V> {
   private class BSTNode{
       private K key;
       private V value;
       private BSTNode left;
       private BSTNode right;
       private int size;
       public BSTNode(K key, V value) {
           this.key = key;
           this.value = value;
           this.size = 1;
       }
   }
   private BSTNode root;


    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();

    }

    public void printInOrder(){
        printInOrder(root);
    }

    private void printInOrder(BSTNode node) {
        if (node == null) {
            return;
        }
        printInOrder(node.left);
        System.out.print(node.key + " "+node.value);
        printInOrder(node.right);
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        BSTNode node = getNode(key,root);
        return node != null;
    }

    @Override
    public V get(K key) {
            BSTNode node= getNode(key,root);
            return node==null?null:node.value;
    }


    private BSTNode getNode(K key,BSTNode  node) {
        if(node == null){
            return null;
        }
        int com = key.compareTo(node.key);
        if(com < 0){
            return getNode(key,node.left);
        } else if (com > 0) {
            return getNode(key,node.right);
        }
        else {
            return node;
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    @Override
    public void put(K key, V value) {
        root = put(key, value, root);
    }

    private BSTNode put(K key, V value, BSTNode node) {
        if(node == null){
            return new BSTNode(key,value);
        }
        int com = key.compareTo(node.key);
        if(com < 0){
            node.left = put(key,value,node.left);
        } else if (com > 0) {
            node .right = put(key,value,node.right);
        }else{
            node.value = value;
        }
        node.size = 1+ size(node.left)+size(node.right);
        return node;
    }

    private int size(BSTNode node) {
        if (node ==null){
            return 0;
        }
        return node.size;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        Iterator<K> iterator = this.iterator();
        while(iterator.hasNext()){
            keySet.add(iterator.next());
        }
        return keySet;
    }

    @Override
    public V remove(K key) {
        BSTNode node = getNode(key, root);
        if(node == null){
            return null;
        }
        V value = node.value;
        root = remove(key,root);
        return value;

    }

    @Override
    public V remove(K key, V value) {
        BSTNode node = getNode(key, root);
        if(node != null&&Objects.equals(value,node.value)){
            root = remove(key,root);
            return value;
        }
        return null;
    }

    public BSTNode remove(K key, BSTNode node) {
        if(node == null){
            return null;
        }
        int com = key.compareTo(node.key);
        if(com < 0){
            node.left = remove(key, node.left);
        } else if (com > 0) {
            node .right = remove(key, node.right);
        }else{
            if(node.left == null){
                return node.right;
            } else if (node.right == null){
                return node.left;
            }else{
                BSTNode successor = min(node.right);
                node.key = successor.key;
                node.value = successor.value;
                node.right = remove(successor.key, node.right);
            }
        }
            node.size = 1+ size(node.left)+size(node.right);
            return node;

    }

    public BSTNode min(BSTNode node) {
        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    private class BSTMapIterator implements Iterator<K> {
        private Stack<BSTNode> stack;
        public BSTMapIterator() {
            stack = new Stack<>();
            pushLeftChildren(root);
        }

        private void pushLeftChildren(BSTNode node) {
            while (node != null){
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {

            return ! stack.isEmpty();
        }

        @Override
        public K next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            BSTNode node = stack.pop();
            if(node.right != null){
                pushLeftChildren(node.right);
            }
            return node.key;
        }
    }
}
