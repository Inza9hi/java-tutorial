package com.github.inza9hi.tutorial.algorithm.leetcode.p146;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
class Node{
     Integer key;
     Integer value;
     Node next;
     Node pre;
}

public class LRUCache {

    private Map<Integer,Node> map;
    private Node head;
    private int size;
    private int capacity;
    private Node tail;



    public LRUCache(int capacity) {
        map = new HashMap<>(capacity);
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
        this.capacity = capacity;

    }

    public int get(int key) {
        Node node = map.get(key);
        if(node ==null){
            return -1;
        }
        removeToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if(node !=null){
           removeToHead(node);
           node.value = value;
        }else {
            Node newNode = new Node();
            newNode.value = value;
            newNode.key = key;

            Node temp = head.next;
            head.next= newNode;
            newNode.pre = head;

            newNode.next = temp;
            temp.pre = newNode;

            if(size+1>capacity){
                removeLast();
            }
            size++;
            map.put(key,newNode);

        }

    }

    private void removeToHead(Node node){
        node.pre.next=node.next;

        Node temp = head.next;
        head.next= node;
        node.pre = head;

        node.next = temp;
        temp.pre = node;

    }

    private void removeLast(){
        Node last = tail.pre;
        tail.pre = tail.pre.pre;
        tail.pre.next = tail;
        map.remove(last.key);

    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache( 2 /* 缓存容量 */ );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // 返回  1
        cache.put(3, 3);    // 该操作会使得关键字 2 作废
        cache.get(2);       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得关键字 1 作废
        int i2 = cache.get(1);// 返回 -1 (未找到)
        System.out.println(i2);
        int i1 = cache.get(3);// 返回  3
        System.out.println(i1);
        int i = cache.get(4);// 返回  4
        System.out.println(i);



    }

}
