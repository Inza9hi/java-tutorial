package com.github.inza9hi.tutorial.algorithm.interview;

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

import com.github.inza9hi.tutorial.algorithm.leetcode.P127词语接龙;
import com.google.common.collect.Maps;

import java.util.*;

public class P127 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Map<String, Set<String>> map = new HashMap<>();
//        wordList.add(beginWord);
        for (String s : wordList) {
            for (String s1:wordList){
                if(isNext(s,s1)){
                    Set<String> strings = map.get(s);
                    if(strings==null){
                        strings = new HashSet<>();
                        strings.add(s1);
                        map.put(s,strings);
                    }else {
                        strings.add(s1);

                    }
                }
            }
        }
        for (String s1:wordList){
            if(isNext(beginWord,s1)){
                Set<String> strings = map.get(beginWord);
                if(strings==null){
                    strings = new HashSet<>();
                    strings.add(s1);
                    map.put(beginWord,strings);
                }else {
                    strings.add(s1);

                }
            }
        }
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int count =1;
        HashSet<String> visited = new HashSet<>();

        while (!queue.isEmpty()){
            String word = queue.poll();
            Set<String> strings = map.get(word);
            if(strings==null){
                continue;
            }
            if(strings.contains(endWord)){
                return count;
            }

            for (String string : strings) {
                if(visited.contains(string)){
                    continue;
                }
                visited.add(string);
                queue.add(string);
            }
            System.out.println(word);
            count++;

        }


        return 0;

    }

    public int doit(String begin,String end, Set<String> visited, List<String> wordList,int count,int maxCount){
//        Set<String> strings = map.get(begin);
//        if(strings==null){
//            return 0;
//        }
//        if(strings.contains(end)){
//            return count+1;
//        }
        for (String string : wordList) {
            if(visited.contains(string)){
                continue;
            }
            if(isNext(begin,string)){
                if(string.equals(end)){
                    if(count+1<maxCount){
                        return count+1;
                    }else {
                        return maxCount;
                    }
                }
                visited.add(string);
                doit(string,end,visited,wordList,count+1,maxCount);
                visited.remove(string);
            }
       }
        return 0;
    }



    public boolean isNext(String s1, String s2){
        if(s1.length()!=s2.length()){
            return false;
        }
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        int count= 0;
        for (int i = 0; i < s1.length(); i++) {
            if(chars1[i]!=chars2[i]){
                count ++;
            }
            if(count>=2){
                return false;
            }
        }
        if(count==1){
            return true;
        }
        return false;


    }

    public static void main(String[] args) {
        P127 p127 = new P127();

        String beginWord = "hit",
                endWord = "cog";
//        String[] wordList = {"ait", "aot", "hot", "dot", "dog", "lot", "log", "cog"};

//
//    beginWord = "hit";
    endWord = "cod";//["hot","dot","dog","lot","log","cog"]
        String[] wordList  = {"hot","dot","dog","lot","log","cog"};
//

        final int i = p127.ladderLength(beginWord, endWord, Arrays.asList(wordList));
        System.out.println(i);
    }
}
