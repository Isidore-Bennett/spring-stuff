package com.coughy.maybe.aspect;

import com.coughy.maybe.dto.DummyUser;
import com.coughy.maybe.resource.AccountResource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class SecurityAspect {

    private final static String pointcutExpr = "execution(* com.example.myApp.myMethod(..)) || execution(* com.example.myApp.mySecondMethod(..))";
    private final static String pointcutExprq = "execution(* com.coughy.maybe.resource.AccountResource.*(..)";

    @Before(pointcutExprq)
    public void asd(JoinPoint joinPoint) throws Exception {
        System.out.println(joinPoint.getSignature());
    }

    public SecurityAspect() {
        System.out.println(DummyUser.class.getCanonicalName());
    }
}
