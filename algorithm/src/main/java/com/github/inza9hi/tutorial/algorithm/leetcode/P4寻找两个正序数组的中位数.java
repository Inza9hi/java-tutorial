package com.github.inza9hi.tutorial.algorithm.leetcode;

public class P4寻找两个正序数组的中位数 {
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    if(nums1==null|| nums1.length==0){
      return findMedianSortedArrays(nums2);
    }
    if(nums2==null|| nums2.length==0){
      return findMedianSortedArrays(nums1);
    }
    int size = nums1.length+nums2.length;
    boolean isOdd = false;
    int mid = size/2;
    if(size%2!=0){
      isOdd = true ;
    }
    int index = 0;
    int i1 = 0;
    int i2 = 0;
    int value = -1 ;
    int preValue =-1;
    while(index<=mid){
       if(index == mid-1){
      preValue = value;
       }
      if(i1<nums1.length&&i2<nums2.length){
        if(nums1[i1]>nums2[i2]){
          index = index+1;
          value = nums2[i2];
          i2 = i2 +1 ;

        }else{
          index = index+1;
          value = nums1[i1];
          i1 = i1 +1 ;

        }
        continue;
      }
      if(i1<nums1.length){
        index = index +1;
        value = nums1[i1];
        i1 = i1+1;

      }
      if(i2<nums2.length){
        index = index +1;
        value = nums2[i2];
        i2 = i2+1;
      }

    }
    if(isOdd){
      return value;
    }else{
      return (double) (value+preValue)/2;
    }
  }
  public double findMedianSortedArrays(int[] nums){
    int mid = nums.length/2;

    if(nums.length%2==0){
      return (nums[mid-1]+nums[mid])/2;
    }else{
      return nums[mid];
    }
  }

  public static void main(String[] args) {
    P4寻找两个正序数组的中位数 p4寻找两个正序数组的中位数 = new P4寻找两个正序数组的中位数();
    int[] nums1 = {1,2};
    int[] nums2 = {3,4};
   double res =  p4寻找两个正序数组的中位数.findMedianSortedArrays(nums1,nums2);
    System.out.println(res);
  }

}
