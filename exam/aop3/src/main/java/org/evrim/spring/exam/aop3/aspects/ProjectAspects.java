package org.evrim.spring.exam.aop3.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProjectAspects {

    @Pointcut("within(org.evrim..beans.*) && @target(org.springframework.stereotype.Service)")
    void services() {
    }

    // method annotations
    @Pointcut("services() && @annotation(org.evrim.spring.exam.aop3.annotations.InTransaction)")
    void transactionalServices() {
    }

    // class annotations
    @Pointcut("services() && @within(org.evrim.spring.exam.aop3.annotations.Loggable)")
    void loggableServicesWithin() {
    }

    // class annotations which have "runtime" retention
    @Pointcut("services() && @target(org.evrim.spring.exam.aop3.annotations.Loggable)")
    void loggableServicesTarget() {
    }

    @Pointcut("services() && target(org.evrim.spring.exam.aop3.beans.OrderService)")
    void orderService() {
    }

    @Pointcut("services() && within(org..*OrderService)")
    void allOrderServices() {
    }

    @Pointcut("services() && args(org.evrim.spring.exam.aop3.data.Order,..)")
    void orderParam() {
    }

    @Pointcut("services() && execution(* org..*.process*(..))")
    void processMethods() {
    }

    @Pointcut("services() && this(org.evrim.spring.exam.aop3.beans.INotifyCustomer)")
    void notifyCustomer() {
    }

    // ADVICES

    @Before("services()")
    public void beforeServices(JoinPoint joinPoint) {
        System.out.println("... ProjectAspects.beforeServices for " + joinPoint.getSignature());
    }

    @Before("transactionalServices()")
    public void beforeTrxServices(JoinPoint joinPoint) {
        System.out.println("... ProjectAspects.beforeTrxServices for " + joinPoint.getSignature());
    }

    @Before("loggableServicesWithin()")
    public void beforeLoggableServices(JoinPoint joinPoint) {
        System.out.println("... ProjectAspects.beforeLoggableServices (@within) for " + joinPoint.getSignature());
    }

    @Before("loggableServicesTarget()")
    public void beforeLoggableServices2(JoinPoint joinPoint) {
        System.out.println("... ProjectAspects.beforeLoggableServices (@target) for " + joinPoint.getSignature());
    }

    @Before("orderService()")
    public void beforeOrderService(JoinPoint joinPoint) {
        System.out.println("... ProjectAspects.beforeOrderService for " + joinPoint.getSignature());
    }

    @Before("allOrderServices()")
    public void beforeAllOrderServices(JoinPoint joinPoint) {
        System.out.println("... ProjectAspects.beforeAllOrderServices for " + joinPoint.getSignature());
    }

    @Before("orderParam()")
    public void beforeOrderParam(JoinPoint joinPoint) {
        System.out.println("... ProjectAspects.beforeOrderParam for " + joinPoint.getSignature());
    }

    @Before("processMethods()")
    public void beforeProcessMethods(JoinPoint joinPoint) {
        System.out.println("... ProjectAspects.beforeProcessMethods for " + joinPoint.getSignature());
    }

    @Before("notifyCustomer()")
    public void beforeNotifyCustomer(JoinPoint joinPoint) {
        System.out.println("... ProjectAspects.beforeNotifyCustomer for " + joinPoint.getSignature());
    }

}
