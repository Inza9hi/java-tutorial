package com.github.inza9hi.tutorial.algorithm.leetcode;

import com.google.common.collect.Lists;
import java.util.List;

//
//
//给定一个 没有重复 数字的序列，返回其所有可能的全排列。
//
//    示例:
//
//    输入: [1,2,3]
//    输出:
//    [
//    [1,2,3],
//    [1,3,2],
//    [2,1,3],
//    [2,3,1],
//    [3,1,2],
//    [3,2,1]
//    ]
//    Related Topics
//    回溯算法
public class P46全排列2 {




  public void backtrack(int[] nums, boolean[] contained,List<Integer> selected) {
    if(nums.length==selected.size()){
      System.out.println(selected);
    }

    for (int i = 0; i < nums.length; i++) {
      if(contained[i]){
        continue;
      }
      selected.add(nums[i]);
      contained[i] = true;
      backtrack(nums,contained,selected);
      selected.remove(selected.size()-1);
      contained[i] = false;
    }

  }

  public static void main(String[] args) {
    int[] nums = {1, 2, 3,4};


    final P46全排列2 p46全排列 = new P46全排列2();
    final List<Integer> integers = Lists.newLinkedList();
    boolean contains[] = new boolean[nums.length];
    p46全排列.backtrack(nums, contains, integers);
  }


}
