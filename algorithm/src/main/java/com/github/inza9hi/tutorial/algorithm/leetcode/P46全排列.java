package com.github.inza9hi.tutorial.algorithm.leetcode;

import com.google.common.collect.Lists;
import java.util.LinkedList;
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
public class P46全排列 {

//  public List<List<Integer>> permute(int[] nums) {
//
//  }

  public void gen(int[] nums, boolean[] contained,List<Integer> selected) {
    if (selected.size() == nums.length) {
      System.out.println(selected);
      return;
    }
    for (int i = 0; i < nums.length; i++) {
      if(contained[i]){
        continue;
      }
      contained[i] = true;
      selected.add(nums[i]);
//      List<Integer> lists = new LinkedList<>(selected);
      gen(nums, contained, selected);
      contained[i] = false;
      selected.remove(selected.size() - 1);
    }


  }

  public static void main(String[] args) {
    int[] nums = {1, 2, 3};

    final P46全排列 p46全排列 = new P46全排列();
    final List<Integer> integers = Lists.newLinkedList();
    boolean contains[] = new boolean[nums.length];
    p46全排列.gen(nums, contains, integers);
  }


}
