package com.github.inza9hi.tutorial.algorithm.leetcode;

public class P238移动0 {

  public static void moveZeroes(int[] nums) {
    if(nums==null) {
      return;
    }
    //两个指针i和j
    int j = 0; //表示的是非0的位置
    for(int i=0;i<nums.length;i++) {
      //当前元素!=0，就把其交换到左边，等于0的交换到右边
      if(nums[i]!=0) {
        if(i!=j){
          int tmp = nums[i];
          nums[i] = nums[j];
          nums[j] = tmp;
        }
        j++;
      }
    }
  }
  public static void moveZeroes2(int[] num) {
    int zeroCnt = 0;
    for(int i = 0 ; i < num.length; ++i){
      if (num[i] == 0){
        zeroCnt++;
      }
      else{
        num[i - zeroCnt] = num[i];
        num[i] = 0;
      }
    }
  }

  public static void main(String[] args) {
//    int[] nums = {0,1,0,3,12};
    int[] nums = {1,1,0,3,12};
    moveZeroes(nums);
    for (int num : nums) {
      System.out.println(num);
    }
  }
  //0 1 0 3 12
  //1 0 0 3 12
  //1 0 0 3 12
  //1 3 0 0 12
  //1 3 12 0 0

  //1,1,0,3,12
  //1,1,0,3,12
  //1,1,3,0,12
  //1,1,3,12,0

}
