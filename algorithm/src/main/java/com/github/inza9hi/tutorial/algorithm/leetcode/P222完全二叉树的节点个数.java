package com.github.inza9hi.tutorial.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }
public class P222完全二叉树的节点个数 {
  public int countNodes(TreeNode root) {
    if(root==null){
      return 0;
    }
    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    int count= 1;
    while (!q.isEmpty()){

      final TreeNode poll = q.poll();
      if(poll!=null){

        if(poll.left!=null){
          q.add(poll.left);
          count++;
        }
        if(poll.right!=null){
          q.add(poll.right);
          count++;

        }
      }
    }
    return count;
  }

}
