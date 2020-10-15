package com.github.inza9hi.tutorial.algorithm.leetcode;

import java.util.LinkedList;
import java.util.List;

public class P22括号生成 {

  public List<String> getStrings(int nums){
    List<String> res = new LinkedList<>();
    List<Character> selected = new LinkedList<>();
    char[] chars = new char[nums*2];
    boolean[] visted = new boolean[nums*2];
    for (int i = 0; i < nums*2; i++) {
      chars[i]='(';
      i=i+1;
      chars[i]=')';
    }
    System.out.println(chars);

    backtrack(chars,visted,res,selected);

    return res;

  }




  public List<String> backtrack(char[] nums,boolean[] visted, List<String> res,List<Character> selected){

    if(selected.size()==nums.length){
      StringBuilder sb = new StringBuilder();
      for (Character character : selected) {
        sb.append(character);
      }
      res.add(sb.toString());
    }

    for (int i = 0; i < nums.length; i++) {
      if(visted[i]){
        continue;
      }

      visted[i] = true;
      selected.add(nums[i]);
      if(isOk(selected)){
        backtrack(nums,visted,res,selected);
      }
      selected.remove(selected.size()-1);
      visted[i] = false;

    }

    return res;


  }

  public List<String> getStrings2(int nums){
    List<String> res = new LinkedList<>();
    List<Character> selected = new LinkedList<>();


    backtrack2(nums,0,0,res,selected);

    return res;

  }

  public List<String> backtrack2(int nums, int lNums, int rNums, List<String> res,List<Character> selected){

    if(selected.size()==nums*2){
      StringBuilder sb = new StringBuilder();
      for (Character character : selected) {
        sb.append(character);
      }
      res.add(sb.toString());
    }


    for (int i = rNums; i <lNums ; i++) {

      selected.add(')');
      backtrack2(nums,lNums,i+1,res,selected);
      selected.remove(selected.size()-1);
    }

    for (int i = lNums; i <=nums ; i++) {
      if(i>=nums){
        continue;
      }
      selected.add('(');
      backtrack2(nums,i+1,rNums,res,selected);
      selected.remove(selected.size()-1);
    }



    return res;


  }


  boolean isOk(List<Character> selected){
    int b=0;
    int e=0;
    for (Character character : selected) {
      if(character.equals('(')){
        b=b+1;
      }else {
        e=e+1;
      }
    }
    if(e>b){
      return false;
    }
    return true;
  }

  public static void main(String[] args) {
    P22括号生成 p22括号生成 = new P22括号生成();
    final List<String> strings = p22括号生成.getStrings2(3);
    System.out.println(strings);
  }


}
