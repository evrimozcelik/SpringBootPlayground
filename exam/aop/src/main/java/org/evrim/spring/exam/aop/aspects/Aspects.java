package org.evrim.spring.exam.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.evrim.spring.exam.aop.beans.MyBean;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class Aspects {

    @Pointcut("execution(* org.evrim..*.set*(*)) || execution(* org.evrim..*.get*())")
    void getterAndSetters() {

    }

    @Before("getterAndSetters()")
    void beforeGettersAndSetters(JoinPoint joinPoint) {
        System.out.println("beforeGettersAndSetters: " + joinPoint.getSignature());
    }

    @Around("within(org.evrim..services.*)")
    Object aroundServices(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("aroundServices: " + proceedingJoinPoint.getSignature());
        Object[] args = proceedingJoinPoint.getArgs();
        Arrays.stream(args).filter(s -> s instanceof MyBean).forEach(b -> updateMyBean((MyBean) b));
        return proceedingJoinPoint.proceed(args);
    }

    private void updateMyBean(MyBean bean) {
        bean.setName(bean.getName()+"1");
        bean.setSurname(bean.getSurname()+"1");
    }

}
