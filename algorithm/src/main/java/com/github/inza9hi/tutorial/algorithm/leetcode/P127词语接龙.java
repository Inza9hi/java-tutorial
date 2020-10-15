package com.github.inza9hi.tutorial.algorithm.leetcode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//输入:
//    beginWord = "hit",
//    endWord = "cog",
//    wordList = ["hot","dot","dog","lot","log","cog"]
//
//    输出: 5
//
//    解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//    返回它的长度 5。
//
//    来源：力扣（LeetCode）
//    链接：https://leetcode-cn.com/problems/word-ladder
//    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Pair {

  String value;
  Integer level;
  Pair parent;

  Pair(String value, Integer level) {
    this.level = level;
    this.value = value;
  }

}

public class P127词语接龙 {

  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    Map<String, List<String>> characterListMap = new HashMap<>();
    for (String s : wordList) {
      for (int i = 0; i < s.length(); i++) {
        final String nextWord = s.substring(0, i) + "*" + s.substring(i + 1, s.length());
//        for (String word : wordList) {
//          if(word.equals(s)){
//            continue;
//          }
//          if(nextWord.equals(word.substring(0, i) + "*" + word.substring(i + 1, s.length()))){
//
//          }
//
        if (characterListMap.containsKey(nextWord)) {
          final List<String> strings = characterListMap.get(nextWord);
          strings.add(s);
        } else {
          final ArrayList<String> strings = new ArrayList<>();
          strings.add(s);
          characterListMap.put(nextWord, strings);
        }

      }

    }

    Queue<Pair> queue = new LinkedList<>();
    queue.add(new Pair(beginWord, 1));

    Set<String> visted = new HashSet<>();
    visted.add(beginWord);
    while (!queue.isEmpty()) {
      final Pair pair = queue.remove();
      String s = pair.value;

      final Integer level = pair.level;
      final Pair parent = pair.parent;
      String ps = null;
      if (parent != null) {
        ps = parent.value;
      }
      System.out.println("remove: " + s + " level : " + level + "parent:" + ps);

      for (int i = 0; i < s.length(); i++) {
        final String nextWord = s.substring(0, i) + "*" + s.substring(i + 1, s.length());
        final List<String> strings = characterListMap.get(nextWord);
        if (strings == null) {
          continue;
        }
        for (String string : strings) {

          if (string.equals(endWord)) {
            System.out.println(endWord);

            System.out.println(s);
            Pair index = parent;
            while (index.parent != null) {
              System.out.println(index.value);
              index = index.parent;
            }
            System.out.println(beginWord);
            return level + 1;
          }
          if (visted.contains(string)) {
            continue;
          }
          final Pair e = new Pair(string, level + 1);
          e.parent = pair;
          queue.add(e);
          visted.add(string);
        }

      }

    }

    return 0;

  }

  public static void main(String[] args) {

    String beginWord = "hit",
        endWord = "cog";
    String[] wordList = {"ait", "aot", "hot", "dot", "dog", "lot", "log", "cog"};

//
//    beginWord = "hit";
//    endWord = "cog";
//    wordList = {"hot","dot","dog","lot","log"};
//

    P127词语接龙 p127词语接龙 = new P127词语接龙();
    final int i = p127词语接龙.ladderLength(beginWord, endWord, Arrays.asList(wordList));
    System.out.println(i);

  }


}
