package com.github.inza9hi.tutorial.poc.aop;

/**
 * Created by lawulu on 15-11-24.
 */

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public  class RetryAdvice implements MethodInterceptor {
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("Before in the advice ");
        Object retVal = invocation.proceed();
        System.out.println("End in the advice ");
        return retVal;
    }
}