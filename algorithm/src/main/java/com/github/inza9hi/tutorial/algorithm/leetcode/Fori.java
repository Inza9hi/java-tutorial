package com.github.inza9hi.tutorial.algorithm.leetcode;

public class Fori {

  public static void main(String[] args) {
    for (int i = 0; i <5; i++) {
      System.out.println(i);
    }

    for (int i = 0; i <5; ++i) {
      System.out.println(i);
    }

    int a = 0;
    for (int i = 0; i < 99; i++) {
      a = a ++;
    }
    System.out.println(a);

    int b = 0;
    for (int i = 0; i < 99; i++) {
      b = ++ b;
    }
    System.out.println(b);

    int c = 0;
    for (int i = 0; i < 99; i++) {
      c++;
    }
    System.out.println(c);

    for (int i = 0; i < 1; i++) {
      System.out.println("once");
    }
  }

}
