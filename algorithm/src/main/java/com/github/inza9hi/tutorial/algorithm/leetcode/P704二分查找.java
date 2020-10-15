package com.github.inza9hi.tutorial.algorithm.leetcode;

public class P704二分查找 {


  public int search(int[] nums, int target) {

    int begin = 0;
    int end = nums.length-1;

    while (begin <= end) {
      if (begin == end) {
        if (nums[begin] == target) {
          return begin;
        } else {
          return -1;
        }
      }
      int mid = begin + (end - begin) / 2;
      if (nums[mid] == target) {
        return mid;
      } else if (nums[mid] < target) {
        begin = mid + 1;
      } else {
        end = mid - 1;
      }
    }
    return -1;
  }
  public int search3(int[] nums, int target){
    int begin=0;
    int end = nums.length-1;
    while (begin<=end){
      if (begin == end) {
        if (nums[begin] == target) {
          return begin;
        } else {
          return -1;
        }
      }
      int mid = begin+(end-begin)/2;

      if(nums[mid]==target){
        return mid;
      }
      if(nums[mid]>target){
        end = mid-1;
      }else {
        begin= mid+1;
      }
   }
    return -1;
  }

  public int search2(int[] nums, int target) {

    return s(nums, 0, (nums.length - 1), target);

  }

  public int s(int[] nums, int begin, int end, int target) {
    if (begin > end) {
      return -1;
    }
    if (begin == end) {
      if (nums[begin] == target) {
        return begin;
      } else {
        return -1;
      }
    }
    int mid = begin + (end - begin) / 2;
    if (nums[mid] == target) {
      return mid;
    } else if (nums[mid] < target) {
      return s(nums, mid + 1, end, target);
    } else {
      return s(nums, begin, mid - 1, target);
    }

  }

  public static void main(String[] args) {
    P704二分查找 p704 = new P704二分查找();
    int[] nums = new int[]{-1, 0, 3, 5, 9, 12};
    int search = p704.search(nums, 9);
    System.out.println(search);

    nums = new int[]{2, 5};
    search = p704.search(nums, 0);
    System.out.println(search);
  }

}
