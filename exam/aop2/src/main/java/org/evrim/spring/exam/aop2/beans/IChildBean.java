package org.evrim.spring.exam.aop2.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public interface IChildBean extends InitializingBean, DisposableBean {

    @PostConstruct
    default void postConstruct() {
        System.out.println("..ChildBean.postConstruct");
    }

    @PreDestroy
    default void preDestroy() {
        System.out.println("..ChildBean.preDestroy");
    }

    @Override
    default public void destroy() throws Exception {
        System.out.println("..ChildBean.destroy");
    }

    @Override
    default public void afterPropertiesSet() throws Exception {
        System.out.println("..ChildBean.afterPropertiesSet");
    }
}
