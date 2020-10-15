package com.github.inza9hi.tutorial.algorithm.leetcode;

public class P70爬楼梯 {
  public int climbStairs(int n) {

    return  cs(n,0);
  }

  public int climbStairs2(int n) {
    if(n == 1)
    {
      return 1;
    }
    else if(n == 2)
    {
      return 2;
    }
    else
    {
      return climbStairs(n - 1) + climbStairs(n - 2);
    }
  }


  public int cs(int n,int currentSteps){


    if(currentSteps>n){
      return 0;
    }
    if(currentSteps==n){
      return 1;
    }
    int a = cs(n,currentSteps+1);
    int b = cs(n,currentSteps+2);
    return a+b;

  }

  public static void main(String[] args) {
    P70爬楼梯 p70 = new P70爬楼梯();
    final int i = p70.climbStairs2(44);
    System.out.println(i);
  }

}
