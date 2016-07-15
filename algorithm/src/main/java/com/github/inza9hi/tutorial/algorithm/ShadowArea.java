package com.github.inza9hi.tutorial.algorithm;

import java.util.Random;

/**
 * Created by lawulu on 16-7-4.
 */
public class ShadowArea {
    //一个正方形的圆形,两个1/4圆,一个1/2圆相交,求相交面积

    public static void main(String[] args) {
        Random random = new Random();
        int n = 10000000;
        int c = 0;
        for (int i = 0; i < n; i++) {
            double px = random.nextDouble();
            double py = random.nextDouble();
            if ((px * px + py * py)<=1 && ((px - 1) * (px - 1) + py * py) <= 1 && (
                    (px - 0.5) * (px - 0.5) + (py - 1) * (py - 1)) <= 0.25) {
                c++;
            }

        }
        System.out.println((double)c/n);



    }
}
