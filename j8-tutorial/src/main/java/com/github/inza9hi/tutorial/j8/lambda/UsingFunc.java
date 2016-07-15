package com.github.inza9hi.tutorial.j8.lambda;

import com.github.inza9hi.tutorial.j8.lambda.func.PrintFunc1;
import com.github.inza9hi.tutorial.j8.lambda.func.PrintFunc2;

/**
 * Created by lawulu on 16-1-12.
 */
public class UsingFunc {

    public static final String P_S ="Hello";

    public void usingFunc1(PrintFunc1 printFunc1){
        printFunc1.print1(P_S);
    }

    public void usingFunc1(PrintFunc2 printFunc2){
        printFunc2.print2(P_S,P_S);

    }

    public static void main(String[] args) {
        UsingFunc usingFunc = new UsingFunc();
        usingFunc.usingFunc1((p)-> System.out.println(p)
        );

    }

}
