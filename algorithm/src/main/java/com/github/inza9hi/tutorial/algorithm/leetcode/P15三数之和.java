package com.github.inza9hi.tutorial.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class P15三数之和 {

  public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> res = new LinkedList<>();
    Set<String> set = new HashSet<>();

    for (int i = 0; i < nums.length; i++) {
      int target = - nums[i];
      final List<List<Integer>> sum = get2sum(target, nums, i);
      if(!sum.isEmpty()){
        for (List<Integer> integers : sum) {
          integers.add(nums[i]);
          Collections.sort(integers);
          final String s = Arrays.toString(integers.toArray(new Integer[]{}));
          if(!set.contains(s)){
            set.add(s);
            res.add(integers);
          }
        }
      }
    }
    return res;
    
  }

  public List<List<Integer>> get2sum(int target, int[] nums, int index){
    Set<Integer> nset = new HashSet<>();
    List<List<Integer>> res = new LinkedList<>();
    for (int i = index+1; i <nums.length ; i++) {
      final int num = target-nums[i];
      if(nset.contains(num)){
        List<Integer> list = new LinkedList<>();
        list.add(nums[i]);
        list.add(num);
        res.add(list);
      }else {
        nset.add(nums[i]);
      }
    }
    return res;

  }
  public List<List<Integer>> threeSum2(int[] nums) {
//    Set<Integer> sets = new HashSet<>();
//    int[] nums = new int[numsi.length];
//    int count =0;
//    for (int i = 0; i < numsi.length; i++) {
//      if(!sets.contains(numsi[0])){
//        sets.add(numsi[0]);
//        nums[count]= numsi[i];
//        count++;
//      }
//    }
    int count = nums.length;
    List<List<Integer>> res  = new ArrayList<>();
    for (int i = 0; i <= count; i++) {
      for (int j = i+1; j <= count; j++) {
        for (int k = j+1; k <= count; k++) {
          if(nums[i]+nums[j]+nums[k]==0){
            List<Integer> list = new ArrayList<>();
            list.add(nums[i]);
            list.add(nums[j]);
            list.add(nums[k]);
            res.add(list);
          }
        }
      }
    }

    return res;

  }

//  public List<List<Integer>> threeSum(int[] nums,int index) {
//    for (int i = 0; i < nums.length; i++) {
//      if(i== index){
//        continue;
//      }
//    }
//
//  }

  public static void main(String[] args) {
    P15三数之和 p15三数之和 = new P15三数之和();
    int nums[] = {-1,0,1,2,-1,-4};
    final List<List<Integer>> lists = p15三数之和.threeSum(nums);
    for (List<Integer> list : lists) {
      System.out.println(list);
    }
  }

  }
