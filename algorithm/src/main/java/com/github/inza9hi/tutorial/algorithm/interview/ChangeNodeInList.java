package com.github.inza9hi.tutorial.algorithm.interview;

public class ChangeNodeInList {

  public static void main(String[] args) {
    Node a = new Node("a");
    Node b = new Node("b");
    Node c = new Node("c");
    Node d = new Node("d");
    Node e = new Node("e");
    a.next(b);
    b.next(c);
    c.next(d);
    d.next(e);
    Node head = a;
    print(head);

    Node node =doit(head);
    print(node);


  }

  public static Node doit(Node head){
    if(head.next!=null){
      Node index = head;
      head = head.next;
      Node prev = null;
      while (index !=null && index.next!=null){

        Node temp = index.next;
        index.next = index.next.next;
        temp.next = index;

        if(prev!=null){
          prev.next = temp;
        }
        prev = index;
        index = index.next;
      }
    }
    return head;

  }
  public static void print(Node index) {
//    Node index = head;
    System.out.println(index.data);
    while (index.next!=null){
      index = index.next;
      System.out.println(index.data);
    }
  }
}
class Node{
  public Object data;
  public Node next;
  public void next(Node next){
    this.next = next;
  }
  public Node(Object data){
    this.data = data;
  }
  @Override
  public String toString(){
    return data.toString();
  }
}