package com.github.inza9hi.tutorial.algorithm.leetcode;

import com.google.common.collect.Lists;
import java.util.LinkedList;
import java.util.List;

public class P22括号生成2 {




  public void backtrack(int nums, int lsize, int rsize, List<Character> selected){
    if(selected.size()==nums*2){
      StringBuilder sb = new StringBuilder();
      for (Character character : selected) {
        sb.append(character);
      }
//      res.add(sb.toString());
      System.out.println(sb.toString());
      return;
    }

    if(lsize<nums){
      selected.add('(');
      backtrack(nums,lsize+1,rsize,selected);
      selected.remove(selected.size()-1);
    }

    if(lsize>rsize && rsize<nums){
      selected.add(')');
      backtrack(nums,lsize,rsize+1,selected);
      selected.remove(selected.size()-1);
    }

  }




  public static void main(String[] args) {
    P22括号生成2 p22括号生成 = new P22括号生成2();

    final LinkedList<Character> se = Lists.newLinkedList();
    p22括号生成.backtrack(3,0,0,se);
//    final List<String> strings = p22括号生成.getStrings2(3);
//    System.out.println(strings);
  }


}
