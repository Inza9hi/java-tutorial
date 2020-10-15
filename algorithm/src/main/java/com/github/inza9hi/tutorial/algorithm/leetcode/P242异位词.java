package com.github.inza9hi.tutorial.algorithm.leetcode;

public class P242异位词 {

  public boolean isAnagram(String s, String t) {
    if(s==null&&t!=null){
      return false;
    }
    if(t==null&&s!=null){
      return false;
    }

    if(s.length()!=t.length()){
      return false;
    }
    final char[] charsT = t.toCharArray();
    final char[] charsS = s.toCharArray();
    for (int i = 0; i < s.length(); i++) {
      if(charsS[i]==charsT[i]){
        continue;
      }
      for (int j = i+1; j <s.length() ; j++) {
        if(charsS[i]==charsT[j]){
          charsT[j] = charsT[i];
          charsT[i] = charsS[i];
          break;
        }
      }
      if(charsS[i]!=charsT[i]){
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    P242异位词 p242异位词 = new P242异位词();
    boolean anagram = p242异位词.isAnagram("cat", "tac");
    System.out.println(anagram);

    anagram = p242异位词.isAnagram("liulu", "luliu");
    System.out.println(anagram);
  }

}
