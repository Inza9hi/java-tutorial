package com.github.inza9hi.tutorial.algorithm.interview;

import com.github.inza9hi.tutorial.algorithm.leetcode.P46全排列2;
import com.google.common.collect.Lists;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P46 {

    public void backtrack(int[] nums, List<List<Integer>> res, List<Integer> list, Set<Integer> contains){
        if(list.size()==nums.length){
            List<Integer> r = Lists.newArrayList();
            r.addAll(list);
            res.add(r);
        }
        for (Integer num : nums) {
            if(contains.contains(num)){
                continue;
            }
            list.add(num);
            contains.add(num);
            backtrack(nums,res,list,contains);
            list.remove(num);
            contains.remove(num);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3,4};


        final P46 p46 = new P46();
        final List<Integer> integers = Lists.newLinkedList();
        List<List<Integer>> res = Lists.newArrayList();
        Set<Integer> contains = new HashSet<>();
        p46.backtrack(nums, res, integers,contains);
        for (List<Integer> re : res) {
            System.out.println(re);
        }
    }
}
