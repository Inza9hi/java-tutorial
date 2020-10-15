package com.github.inza9hi.tutorial.algorithm.leetcode;


class Node {

  public int value;
  public Node left;
  public Node right;

  public Node(int v) {
    this.value = v;
    this.left = null;
    this.right = null;
  }
}

  public class BinaryTreeTraversal {

    /**
     * @param root 树根节点
     * 递归先序遍历
     */
    public static void preOrderRec(Node root){
      if(root!=null){
        System.out.println(root.value);
        preOrderRec(root.left);
        preOrderRec(root.right);
      }
    }
    /**
     * @param root 树根节点
     * 递归中序遍历
     */
    public static void inOrderRec(Node root){
      if(root!=null){
        inOrderRec(root.left);
        System.out.println(root.value);
        inOrderRec(root.right);
      }
    }
    /**
     * @param root 树根节点
     * 递归后序遍历
     */
    public static void postOrderRec(Node root) {
      if (root != null) {
        preOrderRec(root.left);
        preOrderRec(root.right);
        System.out.println(root.value);
      }

    }

    public static void main(String[] args) {
      Node root = new Node(1);
      root.left = new Node(2);
      root.right = new Node(3);

      root.left.left = new Node(4);
      root.left.right = new Node(5);

      root.right.left = new Node(6);
      root.right.right = new Node(7);

      inOrderRec(root);


    }

}
