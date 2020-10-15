package com.github.inza9hi.tutorial.algorithm.interview;

import java.util.LinkedList;
import java.util.List;

public class P22 {
    public List<String> generateParenthesis(int n) {
        List<String> res = new LinkedList<>();
        gen(res, "", 0, 0, n);
        return res;
    }

    public void gen(List<String> res, String str, int leftNum, int rightNum, int n) {
        if (str.length() == 2 * n) {
            res.add(str);
        }
        if (leftNum < n) {
            gen(res, str + "(", leftNum + 1, rightNum, n);
        }
        if (rightNum < n && rightNum < leftNum) {
            gen(res, str + ")", leftNum, rightNum + 1, n);
        }

    }

    public static void main(String[] args) {
        P22 p22括号生成 = new P22();
        final List<String> strings = p22括号生成.generateParenthesis(3);
        System.out.println(strings);
    }

}
